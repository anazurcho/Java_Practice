/**
 * NBGCurrencyService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package currency;

public interface NBGCurrencyService extends javax.xml.rpc.Service {
    public java.lang.String getNBGCurrencyPortAddress();

    public currency.NBGCurrencyPort_PortType getNBGCurrencyPort() throws javax.xml.rpc.ServiceException;

    public currency.NBGCurrencyPort_PortType getNBGCurrencyPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
