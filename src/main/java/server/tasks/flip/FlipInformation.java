package server.tasks.flip;

import server.MainServer;

public class FlipInformation {
    private int buyPrice;
    private int sellPrice;

    /**
     * Givens
     */
    private int itemId;
    private String searchTerm;

    public FlipInformation(int itemId, String searchTerm) {
        this.itemId = itemId;
        this.searchTerm = searchTerm;

        buyPrice = -1;
        sellPrice = -1;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}
