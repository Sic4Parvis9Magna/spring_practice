package commons;

public interface Exceptions {

    @SuppressWarnings("unchecked")
    static <E extends Throwable> void throwChecked(Throwable e) throws E {
        throw (E) e;
    }
}
