public interface iEmployee {

    // If we need a constant variable, please use public static final as its Access Modifier.
    // Access modifier(defaulted): public abstract. Thus, we do not need to claim.

    String getName();

    void setName(String name);

    int getId();

    int getAttendance();

    void setAttendance();

    String toString();
}
