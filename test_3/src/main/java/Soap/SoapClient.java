package Soap;

import currency.NBGCurrencyServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class SoapClient {
    public static String SoapConnect(String currency) throws ServiceException, RemoteException {
        NBGCurrencyServiceLocator locator = new NBGCurrencyServiceLocator();
        return locator.getNBGCurrencyPort().getCurrency(currency);
    }

    public static String SoapConnectDesc(String currency) throws ServiceException, RemoteException {
        NBGCurrencyServiceLocator locator = new NBGCurrencyServiceLocator();
        return locator.getNBGCurrencyPort().getCurrencyDescription(currency);
    }

    public static String GetCurrencyChange(String currency) throws ServiceException, RemoteException {
        NBGCurrencyServiceLocator locator = new NBGCurrencyServiceLocator();
        return locator.getNBGCurrencyPort().getCurrencyChange(currency);
    }
    public static String GetDate(String currency) throws ServiceException, RemoteException {
        NBGCurrencyServiceLocator locator = new NBGCurrencyServiceLocator();
        return locator.getNBGCurrencyPort().getDate(currency);
    }
}
