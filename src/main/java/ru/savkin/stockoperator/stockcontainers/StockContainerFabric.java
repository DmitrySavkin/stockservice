package ru.savkin.stockoperator.stockcontainers;

public class StockContainerFabric {


    public static StockContainer getContainer(int limit) {
        return getContainer(limit, StockContainerType.INC);
    }


    public static StockContainer getContainer(int limit, StockContainerType type) {
        switch (type) {
//            case DESC:
//                return new StockContainerDecrement(limit);
            case INC:
            default:
                return new StockContainerImp(limit);
        }
    }
}
