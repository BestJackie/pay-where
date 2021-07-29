/**
 * FileName: IpUtil
 * Author:   haichaoyang3
 * Date:     2019/6/24 11:32
 * Description: iputil
 * History:
 */
package com.example.paywhere.commom.utils;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

/**
 * Description:〈iputil〉
 *
 * @author haichaoyang3
 * @create 2019/6/24
 * @since 1.0.0
 */
public class IpUtil {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(IpUtil.class);

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static final String IP_TYPE_IP4 = "IP4";
    public static final String IP_TYPE_IP6 = "IP6";

    /**
     * 获取主机IP4，获取不到获取主机名
     *
     * @return
     * @throws Exception
     */
    public static String getLocalIP4() {
        String ip = "";
        try {
            ip = getLocalIP(IP_TYPE_IP4);
        } catch (Exception e) {
            log.error("异常", e);
        }

        return ip;
    }

    /**
     * 获取主机IP6，获取不到获取主机名
     *
     * @return
     * @throws Exception
     */
    public static String getLocalIP6() {
        String ip = "";
        try {
            ip = getLocalIP(IP_TYPE_IP6);
        } catch (Exception e) {
            log.error("异常", e);
        }

        return ip;
    }

    /**
     * 获取主机IP，获取不到获取主机名
     *
     * @return
     * @throws Exception
     */
    public static String getLocalIP(String ipType) throws Exception {

        StringBuffer ipStr = new StringBuffer();

        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;


        if (IP_TYPE_IP4.endsWith(ipType)) {
            while (allNetInterfaces.hasMoreElements()) {

                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        if (!"127.0.0.1".equals(ip.getHostAddress())) {
                            ipStr.append(",").append(ip.getHostAddress());
                        }
                    }
                }
            }
        } else if (IP_TYPE_IP6.endsWith(ipType)) {
            while (allNetInterfaces.hasMoreElements()) {

                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip == null && ip instanceof Inet6Address) {
                        if (!"127.0.0.1".equals(ip.getHostAddress())) {
                            ipStr.append(",").append(ip.getHostAddress());
                        }
                    }
                }
            }
        } else {
            throw new Exception("IP类型错误[" + ipType + "]");
        }


        if ("".equals(ipStr.toString())) {
            InetAddress a = InetAddress.getLocalHost();
            ipStr.append(a.getHostName());
        }

        return ipStr.toString().replaceFirst(",", "");
    }

    /**
     * 获取主机名称
     *
     * @return
     * @throws Exception
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }

}