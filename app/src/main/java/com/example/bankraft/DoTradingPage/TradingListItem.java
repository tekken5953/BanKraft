package com.example.bankraft.DoTradingPage;

public class TradingListItem {
    String person;
    String account;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public TradingListItem(String person, String account) {
        this.person = person;
        this.account = account;
    }
}
