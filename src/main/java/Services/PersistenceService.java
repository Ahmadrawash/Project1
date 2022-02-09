package Services;//package Services;

import DAOs.UsersDAO;

public class PersistenceService {

    private static UsersDAO data;


    static {
        data = new UsersDAO();
    }

    public static UsersDAO getData() {
        return data;
    }

    public static void setData(UsersDAO d) {
        data = d;
    }

    /*
    private static DataDAO data;

    static {
        data = new DataDAO("default", 0, false);
    }

    public static DataDAO getData() {
        return data;
    }

    public static void setData(DataDAO d) {
        data = d;
    }

*/

}
