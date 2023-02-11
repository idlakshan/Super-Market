package dao;

public class DAOFactory {
    static DAOFactory daoFactory;

    private DAOFactory() {

    }
    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
           daoFactory= new DAOFactory();
        }
            return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAIL,QUERYDAO
    }
    public void getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return;
            case ITEM:
                return;
            case ORDER:
                return;
            case ORDERDETAIL:
                return;
            case QUERYDAO:
                return;
        }

    }

}
