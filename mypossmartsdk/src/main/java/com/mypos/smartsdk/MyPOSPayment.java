package com.mypos.smartsdk;


/**
 * Describes a payment
 */
public class MyPOSPayment {

    private boolean     tippingModeEnabled;
    private boolean     motoTransaction;
    private double      productAmount;
    private double      tipAmount;
    private String      foreignTransactionId;
    private Currency    currency;
    private int         printMerchantReceipt;
    private int         printCustomerReceipt;
    private String      operatorCode;
    private String      referenceNumber;
    private int         referenceType;
    private String      motoPassword;


    MyPOSPayment(Builder builder) {
        this.productAmount = builder.productAmount;
        this.foreignTransactionId = builder.foreignTransactionId;
        this.currency = builder.currency;
        this.tippingModeEnabled = builder.tippingModeEnabled;
        this.tipAmount = builder.tipAmount;
        this.motoTransaction = builder.motoTransaction;
        this.printMerchantReceipt = builder.printMerchantReceipt;
        this.printCustomerReceipt = builder.printCustomerReceipt;
        this.operatorCode = builder.operatorCode;
        this.referenceNumber = builder.referenceNumber;
        this.referenceType = builder.referenceType;
        this.motoPassword = builder.motoPassword;
    }


    public static Builder builder() {
        return new Builder();
    }

    public double getProductAmount() {
        return productAmount;
    }

    public MyPOSPayment setProductAmount(double productAmount) {
        this.productAmount = productAmount;
        return this;
    }

    public String getForeignTransactionId() {
        return foreignTransactionId;
    }

    public MyPOSPayment setForeignTransactionId(String foreignTransactionId) {
        this.foreignTransactionId = foreignTransactionId;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public MyPOSPayment setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public boolean isTippingModeEnabled() {
        return tippingModeEnabled;
    }

    public MyPOSPayment setTippingModeEnabled(boolean tippingModeEnabled) {
        this.tippingModeEnabled = tippingModeEnabled;
        return this;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public MyPOSPayment setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
        return this;
    }

    public boolean isMotoTransaction() {
        return motoTransaction;
    }

    public MyPOSPayment setMotoTransaction(boolean motoTransaction) {
        this.motoTransaction = motoTransaction;
        return this;
    }

    public int getPrintMerchantReceipt() {
        return printMerchantReceipt;
    }

    public MyPOSPayment setPrintMerchantReceipt(int printMerchantReceipt) {
        this.printMerchantReceipt = printMerchantReceipt;
        return this;
    }

    public int getPrintCustomerReceipt() {
        return printCustomerReceipt;
    }

    public MyPOSPayment setPrintCustomerReceipt(int printCustomerReceipt) {
        this.printCustomerReceipt = printCustomerReceipt;
        return this;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public MyPOSPayment setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
        return this;
    }

    public String getMotoPassword() {
        return motoPassword;
    }

    public MyPOSPayment setMotoPassword(String motoPassword) {
        this.motoPassword = motoPassword;
        return this;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public int getReferenceType() {
        return referenceType;
    }

    public MyPOSPayment setReference(String referenceNumber, int referenceType) {
        this.referenceNumber = referenceNumber;
        this.referenceType = referenceType;
        return this;
    }

    public static class Builder {
        private boolean     tippingModeEnabled;
        private boolean     motoTransaction;
        private double      tipAmount;
        private Double      productAmount;
        private String      foreignTransactionId;
        private Currency    currency;
        private int         printMerchantReceipt;
        private int         printCustomerReceipt;
        private String      operatorCode;
        private String      referenceNumber;
        private int         referenceType;
        private String      motoPassword;

        public Builder productAmount(Double productAmount) {
            this.productAmount = productAmount;
            return this;
        }

        public Builder tipAmount(Double tipAmount) {
            this.tipAmount = tipAmount;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder foreignTransactionId(String foreignTransactionId) {
            this.foreignTransactionId = foreignTransactionId;
            return this;
        }

        public Builder tippingModeEnabled(boolean tippingModeEnabled) {
            this.tippingModeEnabled = tippingModeEnabled;
            return this;
        }

        public Builder motoTransaction(boolean motoTransaction) {
            this.motoTransaction = motoTransaction;
            return this;
        }

        public Builder printMerchantReceipt(int printMerchantReceipt) {
            this.printMerchantReceipt = printMerchantReceipt;
            return this;
        }

        public Builder printCustomerReceipt(int printCustomerReceipt) {
            this.printCustomerReceipt = printCustomerReceipt;
            return this;
        }

        public Builder operatorCode(String operatorCode) {
            this.operatorCode = operatorCode;
            return this;
        }

        public Builder motoPassword(String motoPassword) {
            this.motoPassword = motoPassword;
            return this;
        }

        public Builder reference(String referenceNumber, int referenceType) {
            this.referenceNumber = referenceNumber;
            this.referenceType = referenceType;
            return this;
        }

        public MyPOSPayment build() {
            if (this.productAmount == null || this.productAmount <= 0.0D) {
                throw new IllegalArgumentException("Invalid or missing amount");
            }
            if (this.currency == null) {
                throw new IllegalArgumentException("Missing currency");
            }

            if (this.tippingModeEnabled && this.tipAmount <= 0.0D) {
                throw new IllegalArgumentException("Invalid tip amount");
            }

            if (operatorCode != null ) {

                boolean valid = true;

                if (operatorCode.length() > 4 || operatorCode.isEmpty()) {
                    valid = false;
                }
                else {
                    try {
                        if(Integer.parseInt(operatorCode) < 0) {
                            valid = false;
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                    }
                }

                if(!valid) {
                    throw new IllegalArgumentException("incorrect operator code");
                }
            }

            if(!ReferenceType.isInBound(referenceType)) {
                throw new IllegalArgumentException("reference type out of bound");
            }
            if(ReferenceType.isEnabled(referenceType) && (referenceNumber == null || referenceNumber.length() > 20 || referenceNumber.isEmpty())) {
                throw new IllegalArgumentException("incorrect reference number");
            }

            return new MyPOSPayment(this);
        }
    }
}
