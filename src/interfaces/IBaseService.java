package interfaces;

import java.util.Scanner;

public interface IBaseService<T> {
    boolean getList(IEdition<T> item);

    boolean menuHandler(Scanner sc, IEdition<T> obj, IInputData<T> inputData);
}
