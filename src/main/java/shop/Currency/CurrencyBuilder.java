package shop.Currency;

public class CurrencyBuilder {
    public static Currency getCurrency(CurrencyCode code) {
        switch(code) {
            case UAH:
                return new Currency("Ukrainian hryvnia",1.0,CurrencyCode.UAH,20.0);
            case USD:
                return new Currency("American dollar",0.36,CurrencyCode.USD,28.0);
            case EUR:
                return new Currency("Euro",0.3,CurrencyCode.EUR,33.0);
            default:
                throw new RuntimeException("Currency not found, sorry");
        }
    }

    public static CurrencyCode convert(Integer index) {
        switch (index) {
            case 1:
                return CurrencyCode.UAH;
            case 2:
                return CurrencyCode.USD;
            case 3:
                return CurrencyCode.EUR;
            default:
                return null;
        }
    }
}
