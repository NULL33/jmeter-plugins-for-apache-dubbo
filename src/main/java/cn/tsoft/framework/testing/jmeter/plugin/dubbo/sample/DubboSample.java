/*
 * Copyright (c) 2018, Tsoft and/or its affiliates. All rights reserved.
 * FileName: DubboSample.java
 * Author:   ningyu
 * Date:     2018年2月8日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.property.IntegerProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import cn.tsoft.framework.testing.jmeter.plugin.util.ClassUtils;
import cn.tsoft.framework.testing.jmeter.plugin.util.JsonUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache.KeyGenerator;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * <功能描述>
 * 
 * @author ningyu
 * @date 2018年2月8日 上午10:09:41
 */
public class DubboSample extends AbstractSampler {
    
    private static final Logger log = LoggingManager.getLoggerForClass();


    /**
     * 
     */
    private static final long serialVersionUID = -6794913295411458705L;
    
    public static String FIELD_DUBBO_PROTOCOL = "FIELD_DUBBO_PROTOCOL";
    public static String FIELD_DUBBO_ADDRESS = "FIELD_DUBBO_ADDRESS";
    public static String FIELD_DUBBO_TIMEOUT = "FIELD_DUBBO_TIMEOUT";
    public static String FIELD_DUBBO_VERSION = "FIELD_DUBBO_VERSION";
    public static String FIELD_DUBBO_RETRIES = "FIELD_DUBBO_RETRIES";
    public static String FIELD_DUBBO_CLUSTER = "FIELD_DUBBO_CLUSTER";
    public static String FIELD_DUBBO_GROUP = "FIELD_DUBBO_GROUP";
    public static String FIELD_DUBBO_CONNECTIONS = "FIELD_DUBBO_CONNECTIONS";
    public static String FIELD_DUBBO_LOADBALANCE = "FIELD_DUBBO_LOADBALANCE";
    public static String FIELD_DUBBO_ASYNC = "FIELD_DUBBO_ASYNC";
    public static String FIELD_DUBBO_INTERFACE = "FIELD_DUBBO_INTERFACE";
    public static String FIELD_DUBBO_METHOD = "FIELD_DUBBO_METHOD";
    public static String FIELD_DUBBO_METHOD_ARGS = "FIELD_DUBBO_METHOD_ARGS";
    public static String FIELD_DUBBO_METHOD_ARGS_SIZE = "FIELD_DUBBO_METHOD_ARGS_SIZE";
    public static String DEFAULT_TIMEOUT = "1000";
    public static String DEFAULT_VERSION = "1.0.0";
    public static String DEFAULT_RETRIES = "0";
    public static String DEFAULT_CLUSTER = "failfast";
    public static String DEFAULT_CONNECTIONS = "100";

    /**
     * 获取 protocol
     * @return the protocol
     */
    public String getProtocol() {
        return this.getPropertyAsString(FIELD_DUBBO_PROTOCOL);
    }

    /**
     * 设置 protocol
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.setProperty(new StringProperty(FIELD_DUBBO_PROTOCOL, protocol));
    }

    /**
     * 获取 address
     * @return the address
     */
    public String getAddress() {
        return this.getPropertyAsString(FIELD_DUBBO_ADDRESS);
    }

    /**
     * 设置 address
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.setProperty(new StringProperty(FIELD_DUBBO_ADDRESS, address));
    }

    /**
     * 获取 timeout
     * @return the timeout
     */
    public String getTimeout() {
        return this.getPropertyAsString(FIELD_DUBBO_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     * 设置 timeout
     * @param timeout the timeout to set
     */
    public void setTimeout(String timeout) {
        this.setProperty(new StringProperty(FIELD_DUBBO_TIMEOUT, timeout));
    }

    /**
     * 获取 version
     * @return the version
     */
    public String getVersion() {
        return this.getPropertyAsString(FIELD_DUBBO_VERSION, DEFAULT_VERSION);
    }

    /**
     * 设置 version
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.setProperty(new StringProperty(FIELD_DUBBO_VERSION, version));
    }
    
    /**
     * 获取 retries
     * @return the retries
     */
    public String getRetries() {
        return this.getPropertyAsString(FIELD_DUBBO_RETRIES, DEFAULT_RETRIES);
    }

    /**
     * 设置 retries
     * @param retries the retries to set
     */
    public void setRetries(String retries) {
        this.setProperty(new StringProperty(FIELD_DUBBO_RETRIES, retries));
    }
    
    /**
     * 获取 cluster
     * @return the cluster
     */
    public String getCluster() {
        return this.getPropertyAsString(FIELD_DUBBO_CLUSTER, DEFAULT_CLUSTER);
    }

    /**
     * 设置 cluster
     * @param cluster the cluster to set
     */
    public void setCluster(String cluster) {
        this.setProperty(new StringProperty(FIELD_DUBBO_CLUSTER, cluster));
    }
    
    /**
     * 获取 group
     * @return the group
     */
    public String getGroup() {
    	return this.getPropertyAsString(FIELD_DUBBO_GROUP, null);
    }
    
    /**
     * 设置 group
     * @param group the group to set
     */
    public void setGroup(String group) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_GROUP, group));
    }
    
    /**
     * 获取 connections
     * @return the group
     */
    public String getConnections() {
    	return this.getPropertyAsString(FIELD_DUBBO_CONNECTIONS, DEFAULT_CONNECTIONS);
    }
    
    /**
     * 设置 connections
     * @param connections the connections to set
     */
    public void setConnections(String connections) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_CONNECTIONS, connections));
    }
    
    /**
     * 获取 loadbalance
     * @return the loadbalance
     */
    public String getLoadbalance() {
    	return this.getPropertyAsString(FIELD_DUBBO_LOADBALANCE);
    }
    
    /**
     * 设置 loadbalance
     * @param loadbalance the loadbalance to set
     */
    public void setLoadbalance(String loadbalance) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_LOADBALANCE, loadbalance));
    }
    
    /**
     * 获取 async
     * @return the async
     */
    public String getAsync() {
    	return this.getPropertyAsString(FIELD_DUBBO_ASYNC);
    }
    
    /**
     * 设置 async
     * @param async the async to set
     */
    public void setAsync(String async) {
    	this.setProperty(new StringProperty(FIELD_DUBBO_ASYNC, async));
    }

    /**
     * 获取 interfaceName
     * @return the interfaceName
     */
    public String getInterface() {
        return this.getPropertyAsString(FIELD_DUBBO_INTERFACE);
    }

    /**
     * 设置 interfaceName
     * @param interfaceName the interfaceName to set
     */
    public void setInterfaceName(String interfaceName) {
        this.setProperty(new StringProperty(FIELD_DUBBO_INTERFACE, interfaceName));
    }

    /**
     * 获取 method
     * @return the method
     */
    public String getMethod() {
        return this.getPropertyAsString(FIELD_DUBBO_METHOD);
    }

    /**
     * 设置 method
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.setProperty(new StringProperty(FIELD_DUBBO_METHOD, method));
    }

    /**
     * 获取 methodArgs
     * @return the methodArgs
     */
    public List<MethodArgument> getMethodArgs() {
    	int paramsSize = this.getPropertyAsInt(FIELD_DUBBO_METHOD_ARGS_SIZE, 0);
    	List<MethodArgument> list = new ArrayList<MethodArgument>();
		for (int i = 1; i <= paramsSize; i++) {
			String paramType = this.getPropertyAsString(FIELD_DUBBO_METHOD_ARGS + "_PARAM_TYPE" + i);
			String paramValue = this.getPropertyAsString(FIELD_DUBBO_METHOD_ARGS + "_PARAM_VALUE" + i);
			MethodArgument args = new MethodArgument(paramType, paramValue);
			list.add(args);
		}
    	return list;
    }

    /**
     * 设置 methodArgs
     * @param methodArgs the methodArgs to set
     */
    public void setMethodArgs(List<MethodArgument> methodArgs) {
    	int size = methodArgs == null ? 0 : methodArgs.size();
    	this.setProperty(new IntegerProperty(FIELD_DUBBO_METHOD_ARGS_SIZE, size));
    	if (size > 0) {
    		for (int i = 1; i <= methodArgs.size(); i++) {
    			this.setProperty(new StringProperty(FIELD_DUBBO_METHOD_ARGS + "_PARAM_TYPE" + i, methodArgs.get(i-1).getParamType()));
    			this.setProperty(new StringProperty(FIELD_DUBBO_METHOD_ARGS + "_PARAM_VALUE" + i, methodArgs.get(i-1).getParamValue()));
    		}
    	}
    }

    @SuppressWarnings("deprecation")
	@Override
    public SampleResult sample(Entry entry) {
        SampleResult res = new SampleResult();
        res.setSampleLabel(getName());
        res.sampleStart();
        //构造请求数据
        res.setSamplerData(getSampleData());
        //调用dubbo
        res.setResponseData(JsonUtils.toJson(callDubbo(res)));
        //构造响应数据
        res.setDataType(SampleResult.TEXT);
        res.setResponseCodeOK();
        res.setResponseMessageOK();
        res.sampleEnd();
        return res;
    }

    /**
     * 构造请求数据
     * 
     * @author ningyu
     * @date 2018年2月9日 上午9:58:24
     *
     * @return
     */
    private String getSampleData() {
    	StringBuilder sb = new StringBuilder();
        sb.append("Protocol: ").append(getProtocol()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("Timeout: ").append(getTimeout()).append("\n");
        sb.append("Version: ").append(getVersion()).append("\n");
        sb.append("Retries: ").append(getRetries()).append("\n");
        sb.append("Cluster: ").append(getCluster()).append("\n");
        sb.append("Group: ").append(getGroup()).append("\n");
        sb.append("Connections: ").append(getConnections()).append("\n");
        sb.append("Loadbalance: ").append(getLoadbalance()).append("\n");
        sb.append("Async: ").append(getAsync()).append("\n");
        sb.append("Interface: ").append(getInterface()).append("\n");
        sb.append("Method: ").append(getMethod()).append("\n");
        sb.append("Method Args: ").append(getMethodArgs().toString());
        return sb.toString();
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object callDubbo(SampleResult res) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("DubboSample");
        
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig reference = new ReferenceConfig();
        // 引用远程服务
        reference.setApplication(application);
        RegistryConfig registry = null;
        
        String protocol = getProtocol().split("@")[0];
		switch (protocol) {
		case "zookeeper":
			registry = new RegistryConfig();
			registry.setProtocol("zookeeper");
			registry.setAddress(getAddress());
			reference.setRegistry(registry);
			break;
		case "multicast":
			registry = new RegistryConfig();
			registry.setProtocol("multicast");
			registry.setAddress(getAddress());
			reference.setRegistry(registry);
			break;
		case "redis":
			registry = new RegistryConfig();
			registry.setProtocol("redis");
			registry.setAddress(getAddress());
			reference.setRegistry(registry);
			break;
		case "simple":
			registry = new RegistryConfig();
			registry.setAddress(getAddress());
			reference.setRegistry(registry);
			break;
		default:
			// 默认dubbo直连
			StringBuffer sb = new StringBuffer();
			sb.append(protocol).append("://").append(getAddress()).append("/")
					.append(getInterface());
			log.debug("rpc invoker url : " + sb.toString());
			reference.setUrl(sb.toString());
		}
        try {
            Class clazz = Class.forName(getInterface());
            reference.setInterface(clazz);
            reference.setRetries(Integer.valueOf(getRetries()));
            reference.setCluster(getCluster());
            reference.setVersion(getVersion());
            reference.setTimeout(Integer.valueOf(getTimeout()));
            String group = getGroup();
            reference.setGroup(StringUtils.isBlank(group) ? null : group);
            reference.setConnections(Integer.valueOf(getConnections()));
            String loadbalance = getLoadbalance().split("@")[0];
            reference.setLoadbalance(loadbalance);
            String async = getAsync().split("@")[0];
            reference.setAsync("async".equals(async) ? true : false);
            reference.setGeneric(true);
            //TODO 不同的注册中心地址使用不同的cache对象
            ReferenceConfigCache cache = ReferenceConfigCache.getCache(getAddress(), new KeyGenerator() {
				public String generateKey(ReferenceConfig<?> referenceConfig) {
					return referenceConfig.toString();
				}
			});
            GenericService genericService = (GenericService) cache.get(reference);
//            GenericService genericService = (GenericService) reference.get();
            Method method = null;
            String[] parameterTypes = null;
            Object[] parameterValues = null;
            List<MethodArgument> args = getMethodArgs();
            List<String> paramterTypeList = null;
            List<Object> parameterValuesList = null;
            Method[] methods = clazz.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];
				Type[] paramTypes = m.getGenericParameterTypes();
				paramterTypeList = new ArrayList<String>();
				parameterValuesList = new ArrayList<Object>();
				log.debug("paramTypes.length="+paramTypes.length+"|args.size()="+args.size());
				if (m.getName().equals(getMethod()) && paramTypes.length == args.size()) {
					//名称与参数数量匹配，进行参数类型转换
					for (int j = 0; j < paramTypes.length; j++) {
						ClassUtils.parseParameter(paramTypes[j], paramterTypeList, parameterValuesList, args.get(j));
					}
					if (parameterValuesList.size() == paramTypes.length) {
						//没有转换错误，数量应该一致
						method = m;
						break;
					}
				}
			}
            if (method == null) {
                res.setSuccessful(false);
                return "Method["+getMethod()+"] Not found!";
            }
            //发起调用
            parameterTypes = paramterTypeList.toArray(new String[paramterTypeList.size()]);
            parameterValues = parameterValuesList.toArray(new Object[parameterValuesList.size()]);
            Object result = null;
			try {
				result = genericService.$invoke(getMethod(), parameterTypes, parameterValues);
				res.setSuccessful(true);
			} catch (Exception e) {
				log.error("接口返回异常：", e);
				//TODO
				//当接口返回异常时，sample标识为successful，通过响应内容做断言来判断是否标识sample错误，因为sample的错误会统计到用例的error百分比内。
				//比如接口有一些校验性质的异常，不代表这个操作是错误的，这样就可以灵活的判断，不至于正常的校验返回导致测试用例error百分比的不真实
				res.setSuccessful(true);
				result = e;
			}
            return result;
        } catch (Exception e) {
            log.error("未知异常：", e);
            res.setSuccessful(false);
            return e;
        } finally {
        	//TODO 不能在sample结束时destroy
//            if (registry != null) {
//                registry.destroyAll();
//            }
//            reference.destroy();
        }
    }
    
    public static void main(String[] args) throws Exception {
//    	  ApplicationConfig application = new ApplicationConfig();
//        application.setName("DubboSample");
//        
//        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
//        ReferenceConfig reference = new ReferenceConfig();
//        // 引用远程服务
//        reference.setApplication(application);
//        RegistryConfig registry = null;
//        
//        StringBuffer sb = new StringBuffer();
//        sb.append("dubbo").append("://").append("192.168.6.47:20835").append("/").append("com.jiuyescm.tenant.api.IMenuResourceService");
//        log.debug("rpc invoker url : " + sb.toString());
//        reference.setUrl(sb.toString());
//        Class clazz = Class.forName("com.jiuyescm.tenant.api.IMenuResourceService");
//        reference.setInterface(clazz);
//        reference.setRetries(Integer.valueOf("0"));
//        reference.setCluster("failfast");
//        reference.setVersion("1.0.0");
//        reference.setTimeout(Integer.valueOf("1200000"));
//        Object target = reference.get();
//        Method method = null;
//        Object[] parameterValues = null;
//        Method[] methods = target.getClass().getMethods();
//    	String methodName = "createMenuResourceMapping";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("java.util.List", "[{\"menuId\":30002,\"appId\":8,\"resourceId\":40052},{\"menuId\":30003,\"appId\":8,\"resourceId\":40052}]"));
//    	Class clazz = Class.forName("com.jiuyescm.tenant.api.IResourceService");
//    	String methodName = "query";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("com.jiuyescm.tenant.vo.ResourceVo", "{\"menuId\":30002,\"appId\":8,\"resourceId\":40052}"));
//    	list.add(new MethodArgument("java.lang.Integer", "1"));
//    	list.add(new MethodArgument("java.lang.Integer", "10"));
//    	List<Object> parameterValuesList = null;
//    	Class clazz = Class.forName("com.jiuyescm.tenant.api.IResourceService");
//    	String methodName = "testMethod";
//    	List<MethodArgument> list = new ArrayList<MethodArgument>();
//    	list.add(new MethodArgument("java.util.Map", "{\"name\":\"name\",\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}}"));
//    	list.add(new MethodArgument("java.util.List", "[{\"name\":1,\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}},{\"name\":2,\"value\":{\"service\":\"test1\",\"url\":\"test\",\"action\":\"GET\",\"enabled\":true,\"isPublic\":false,\"appId\":8,\"menuId\":30001}}]"));
//    	Method[] methods = clazz.getMethods();
//    	parameterValuesList = new ArrayList<Object>();
//    	for (Method m : methods) {
//    		if (m.getName().equals(methodName)) {
//    			Type[] paramTypes = m.getGenericParameterTypes();
//				for (int j = 0; j < paramTypes.length; j++) {
//					ClassUtils.parseParameter(paramTypes[j], parameterValuesList, list.get(j));
//				}
//    		}
//    	}
//		System.out.println(int.class.getName());
	}

}
