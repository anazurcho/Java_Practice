/**
 * NBGCurrencyPort_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package currency;

public interface NBGCurrencyPort_PortType extends java.rmi.Remote {
    public java.lang.String getCurrency(java.lang.String code) throws java.rmi.RemoteException;
    public java.lang.String getCurrencyDescription(java.lang.String code) throws java.rmi.RemoteException;
    public java.lang.String getCurrencyChange(java.lang.String code) throws java.rmi.RemoteException;
    public int getCurrencyRate(int code) throws java.rmi.RemoteException;
    public java.lang.String getDate(String currency) throws java.rmi.RemoteException;
}
