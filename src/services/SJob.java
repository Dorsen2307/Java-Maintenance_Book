package services;

import interfaces.IEdition;

public class SJob<T> extends BaseService<T> {
    @Override
    public boolean getList(IEdition<T> item) {
        item.view();
        super.getSecondMenu();
        return super.inputItemSecondMenu(item);
    }
}
