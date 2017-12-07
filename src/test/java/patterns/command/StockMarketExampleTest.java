package patterns.command;

import lombok.*;
import org.junit.Test;

import java.util.*;

public class StockMarketExampleTest {

    @Test
    public void testCommandPatternByStockMarketExample() {
        Broker broker = new Broker(Market.INSTANCE);

        Stock googleStock = new Stock("Google", 100);
        Stock appleStock = new Stock("Apple", 60);

        Order sellGStock = new SellStock(googleStock);
        Order sellAStock = new SellStock(appleStock);
        Order buyGStock = new BuyStock(googleStock.cloneWithQuantity(50));

        broker.take(sellGStock);
        broker.take(sellAStock);
        broker.take(buyGStock);

        broker.placeOrdersOnMarket();

    }

    private class Broker {
        Queue<Order> orders = new ArrayDeque<>();
        Market market;

        private Broker(Market market) {
            this.market = market;
        }

        void take(Order order) {
            order.setMarket(market);
            orders.offer(order);
        }

        void placeOrdersOnMarket() {
            for (Order order : orders) {
                order.execute();
            }
        }
    }

    private interface Order {
        boolean execute();
        void setMarket(Market market);
    }

    @RequiredArgsConstructor
    @Setter
    private class SellStock implements Order {
        final Stock stock;
        Market market;

        @Override
        public boolean execute() {
            return market.put(stock);
        }
    }

    @RequiredArgsConstructor
    @Setter
    private class BuyStock implements Order {
        final Stock stock;
        Market market;

        @Override
        public boolean execute() {
            return market.pull(stock);
        }
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode(exclude = {"quantity"})//for set in market
    private class Stock implements Cloneable {
        private final String name;
        private int quantity;

        Stock cloneWithQuantity(int quantity) {
            Stock clone = (Stock) clone();
            clone.setQuantity(quantity);
            return clone;
        }

        @Override
        protected Object clone() {
            Object clone = null;

            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            return clone;
        }
    }

    private enum Market {
        INSTANCE;

        private Map<String, Stock> stocks = new HashMap<>();

        boolean put(Stock stock) {
            Optional<Stock> prevStock = Optional.ofNullable(stocks.get(stock.getName()));
            if(prevStock.isPresent()) {
                stocks.put(stock.getName(), stock.cloneWithQuantity(stock.getQuantity() + prevStock.get().getQuantity()));
            } else {
                stocks.put(stock.getName(), stock);
            }
            System.out.println("The stock " + stock + " have just added");
            return !prevStock.isPresent();
        }

        boolean pull(Stock stock) {
            Optional<Stock> prevStock = Optional.ofNullable(stocks.get(stock.getName()));
            boolean canPull = prevStock.isPresent() && prevStock.get().getQuantity() > stock.getQuantity();
            if(canPull) {
                int newQuantity = prevStock.get().getQuantity() - stock.getQuantity();
                stocks.put(stock.getName(), stock.cloneWithQuantity(newQuantity));
                System.out.println("The stock " + stock + " have just removed from the market");
                System.out.println("The " + newQuantity + " " + stock.getName() + " stocks left in the market");
            } else {
                System.out.println("The stock " + stock + " can't be bought in the market");
            }

            return canPull;
        }
    }



}
