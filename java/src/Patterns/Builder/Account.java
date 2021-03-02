package Patterns.Builder;

public class Account {
    private long id;
    private int password;
    private String description;

    private Account() {

    }

    public long getId() {
        return id;
    }

    public int getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public static Builder createBuilder() {
        return new Account().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(long id) {
            Account.this.id = id;
            return this;
        }

        public Builder setPassword(int password) {
            Account.this.password = password;
            return this;
        }

        public Builder setDescription(String description) {
            Account.this.description = description;
            return this;
        }

        public Account build() {
            return Account.this;
        }
    }

    Account account = Account.createBuilder()
            .setId(3151351L)
            .setPassword(12321451)
            .setDescription("Account for personal usage")
            .build();
}