package KafkaChat;

import java.lang.Object;
import java.lang.String;
import java.util.*;

import kafka.consumer.*;
import kafka.javaapi.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaUser {
    private String zookeeper;
    private String nickname;
    private String bootstrapBroker;
    private List<String> channelList = new ArrayList<String>();

    public KafkaUser(String zookeeper, String bootstrapBroker) {
        this.zookeeper = zookeeper;
        this.bootstrapBroker = bootstrapBroker;
    }

    public void login(String nickname) {
        if (this.nickname != null) {
            exit();
        }
        this.nickname = nickname;
        ConsumerConfig config = createConsumerConfig(this.zookeeper, getCustomerGroup());
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(getUserChannelsTopic(), 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = connector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(getUserChannelsTopic());

        for (KafkaStream<byte[], byte[]> stream : streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            try {
                while (it.hasNext()) {
                    String channel = new String(it.next().message());
                    if (channel.equals("  ")) {
                        channelList.clear();
                    } else {
                        channelList.add(channel);
                    }
                }
            } catch (Exception e) {
                System.out.println("");
            }
        }

        connector.shutdown();
    }

    public void join(String channel) {
        if (!channelList.contains(channel)) {
            channelList.add(channel);
            System.out.println("Joined " + channel);
        } else {
            System.out.println("Already joined " + channel);
        }

    }

    public void leave(String channel) {
        if (channelList.contains(channel)) {
            channelList.remove(channel);
            System.out.println("Leave " + channel);
        } else {
            System.out.println("Not joined " + channel);
        }
    }

    public void send(String channelName, String message) {
        KafkaProducer producer = getProducer();
        String modifiedMessage = "[" + nickname + "][" + channelName + "]" + message;
        ProducerRecord<byte[], byte[]> record =
                new ProducerRecord<byte[], byte[]>(getChannelTopicName(channelName),
                        modifiedMessage.getBytes());
        producer.send(record);
        producer.close();
    }

    public void sendAll(String message) {
        KafkaProducer producer = getProducer();
        String modifiedMessage = "[broadcast][" + nickname + "]" + message;
        for (String channel : channelList) {
            ProducerRecord<byte[], byte[]> record =
                    new ProducerRecord<byte[], byte[]>(getChannelTopicName(channel),
                            modifiedMessage.getBytes());
            producer.send(record);
        }
        producer.close();
    }

    public List<String> recieveMessages() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        for (String channel : channelList) {
            topicCountMap.put(getChannelTopicName(channel), 1);
        }

        ConsumerConfig config = createConsumerConfig(this.zookeeper, getCustomerGroup());
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = connector.createMessageStreams(topicCountMap);

        List<String> messages = new ArrayList<String>();
        for (String channel : channelList) {
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(getChannelTopicName(channel));

            for (KafkaStream<byte[], byte[]> stream : streams) {
                ConsumerIterator<byte[], byte[]> it = stream.iterator();
                try {
                    while (it.hasNext())
                        messages.add(new String(it.next().message()));
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        }
        connector.shutdown();

        return messages;
    }

    public void exit() {
        KafkaProducer producer = getProducer();
        ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(getUserChannelsTopic(), "  ".getBytes());
        producer.send(record);
        for (String channel : channelList) {
            record = new ProducerRecord<byte[], byte[]>(getUserChannelsTopic(), channel.getBytes());
            producer.send(record);
        }
        producer.close();
        this.nickname = null;
        this.channelList = new ArrayList<String>();
    }

    private KafkaProducer getProducer() {
        Map<java.lang.String, java.lang.Object> configs = new HashMap<String, Object>();
        configs.put("bootstrap.servers", bootstrapBroker);
        configs.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        return new KafkaProducer(configs);
    }

    private String getChannelTopicName(String channel) {
        return "channel - " + channel;
    }


    private String getUserChannelsTopic() {
        return "channel list - " + nickname;
    }

    private String getCustomerGroup() {
        return "customer - " + nickname;
    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("consumer.timeout.ms", "2000");
        props.put("auto.offset.reset", "smallest");
        return new ConsumerConfig(props);
    }
}