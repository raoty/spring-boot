/*
 * @(#) BeanUtils.java  1.0 2016��4��11��
 *
 * Copyright 2016 Client Server International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ngp.core.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * BeanUtils.java
 * version 1.0
 * since 1.0
 */
public class BeanUtils
{
  static ThreadLocal<Set> recurseBeanSet = new ThreadLocal()
  {
    protected synchronized Set initialValue() {
      return new HashSet();
    }
  };

  public static <T> T map2Bean(Map map, T obj)
  {
    PropertyDescriptor[] arrayOfPropertyDescriptor1;
    BeanWrapper bw = new BeanWrapperImpl(obj);
    PropertyDescriptor[] props = bw.getPropertyDescriptors();
    int j = (arrayOfPropertyDescriptor1 = props).length; for (int i = 0; i < j; ++i) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[i];
      String name = pd.getName();

      if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name)))
      {
        String convertedName;
        Object value;
        Class class0 = pd.getPropertyType();
        if (Enum.class.isAssignableFrom(class0))
        {
          convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
          value = map.get(convertedName);

          if (value != null)
          {
            if (value.getClass() == class0) {
              bw.setPropertyValue(name, value);
            }
            else {
              String enumValue = String.valueOf(value);
              if (enumValue.length() > 0)
              {
                Enum v = Enum.valueOf(class0, enumValue);
                bw.setPropertyValue(name, v);
              }
            }
          }
        }
        else
        {
          convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
          value = map.get(convertedName);

          if (value != null)
          {
            bw.setPropertyValue(name, value);
          }
        }
      }
    }
    return (T) bw.getWrappedInstance();
  }

  public static <T> T map2Bean(Map map, Class<T> clazz)
  {
    PropertyDescriptor[] arrayOfPropertyDescriptor1;
    BeanWrapper bw = new BeanWrapperImpl(clazz);
    PropertyDescriptor[] props = bw.getPropertyDescriptors();
    int j = (arrayOfPropertyDescriptor1 = props).length; for (int i = 0; i < j; ++i) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[i];
      String name = pd.getName();

      if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name)))
      {
        String convertedName;
        Object value;
        Class class0 = pd.getPropertyType();
        if (Enum.class.isAssignableFrom(class0))
        {
          convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
          value = map.get(convertedName);

          if (value != null)
          {
            if (value.getClass() == class0) {
              bw.setPropertyValue(name, value);
            }
            else {
              String enumValue = String.valueOf(value);
              if (enumValue.length() > 0)
              {
                Enum v = Enum.valueOf(class0, String.valueOf(value));
                bw.setPropertyValue(name, v);
              }
            }
          }
        }
        else
        {
          convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
          value = map.get(convertedName);

          if (value != null)
          {
            bw.setPropertyValue(name, value);
          }
        }
      }
    }
    return (T) bw.getWrappedInstance();
  }

  public static Map bean2Map(Object beanObject)
  {
    BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
    PropertyDescriptor[] desc = bean.getPropertyDescriptors();
    Map dataMap = new HashMap(desc.length);
    try
    {
      for (int i = 0; i < desc.length; ++i)
      {
        String name = desc[i].getName();

        if ((bean.isWritableProperty(name)) && (bean.isReadableProperty(name)))
        {
          Object object = bean.getPropertyValue(name);
          if (object == null)
            continue;

          String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
          dataMap.put(convertedName, object);
        }

      }

      return dataMap;
    }
    catch (Exception e1) {
      throw new RuntimeException(
              "ngp.core.util.bean2bean_fail",
              e1);
    }
  }

  public static List<Map> listBean2ListMap(List list)
  {
    List result = new ArrayList();

    for (Iterator it = list.iterator(); it.hasNext(); ) {
      Map tmp = bean2Map(it.next());
      result.add(tmp);
    }
    return result;
  }

  public static <T> List<T> listMap2ListBean(List list, Class<T> class0) {
    List result = new ArrayList();
    for (Iterator it = list.iterator(); it.hasNext(); ) {
      Object t = map2Bean((Map)it.next(), class0);
      result.add(t);
    }
    return result;
  }

  public static Map bean2MapRecurse(Object beanObject)
  {
    Set set = (Set)recurseBeanSet.get();

    if (set.contains(beanObject))
      return null;

    set.add(beanObject);
    try {
      Map dataMap = null;
      BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
      PropertyDescriptor[] desc = bean.getPropertyDescriptors();
      try
      {
        for (int i = 0; i < desc.length; ++i)
        {
          String name = desc[i].getName();

          if ((bean.isWritableProperty(name)) && (bean.isReadableProperty(name)))
          {
            Object object = bean.getPropertyValue(name);
            if (object == null)
              continue;

            String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);

            Class class0 = object.getClass();
            if ((class0.getName().startsWith("java")) || (Enum.class.isAssignableFrom(class0)))
            {
              dataMap.put(convertedName, object);
            }
            else
            {
              Map subMap = bean2MapRecurse(object);
              if (subMap != null)
              {
                for (Iterator it = subMap.entrySet().iterator(); it.hasNext(); )
                {
                  Map.Entry entry = (Map.Entry)it.next();

                  dataMap.put(convertedName + "_" + entry.getKey(), entry.getValue());
                }
              }
            }
          }

        }

        Map localMap1 = dataMap;

        set.remove(beanObject);

        return localMap1;
      }
      catch (Exception e1) {
        throw e1;
      }
    }
    finally
    {
      set.remove(beanObject);
    }
  }

  public static void list2Bean(List<?> srcBeanObject, Object destBeanObject, String listPropName)
  {
    BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
    destBean.setPropertyValue(listPropName, srcBeanObject);
  }

  public static <T> T bean2Bean(Object srcBeanObject, Class<T> class0) {
    Object t;
    try {
      t = class0.newInstance();
      if (srcBeanObject instanceof List)
      {
        list2Bean((List)srcBeanObject, t, "list");
      }
      else
        bean2Bean(srcBeanObject, t);

      return (T) t;
    }
    catch (Exception e) {
      throw new RuntimeException(
              "ngp.core.util.bean2bean_fail",
              e);
    }

  }

  public static void bean2Bean(Object srcBeanObject, Object destBeanObject)
  {
    BeanWrapperImpl srcBean = new BeanWrapperImpl(srcBeanObject);
    BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);

    PropertyDescriptor[] destDesc = destBean.getPropertyDescriptors();
    try
    {
      for (int i = 0; i < destDesc.length; ++i)
      {
        String name = destDesc[i].getName();

        if ((destBean.isWritableProperty(name)) && 
          (srcBean.isReadableProperty(name)))
        {
          Object srcValue = srcBean.getPropertyValue(name);
          if (srcValue != null) {
            destBean.setPropertyValue(name, srcValue);
          }

        }

      }

    }
    catch (Exception e1)
    {
      throw new RuntimeException(
              "ngp.core.util.bean2bean_fail",
              e1);
    }
  }

  public static void main(String[] args)
  {
	  
  }

  public static <T> T map2BeanStrict(Map map, T obj)
  {
    PropertyDescriptor[] arrayOfPropertyDescriptor1;
    BeanWrapper bw = new BeanWrapperImpl(obj);
    PropertyDescriptor[] props = bw.getPropertyDescriptors();
    int j = (arrayOfPropertyDescriptor1 = props).length; for (int i = 0; i < j; ++i) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[i];
      String name = pd.getName();

      if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name)))
      {
        Object value;
        Class class0 = pd.getPropertyType();
        if (Enum.class.isAssignableFrom(class0))
        {
          value = map.get(name);

          if (value != null)
          {
            if (value.getClass() == class0) {
              bw.setPropertyValue(name, value);
            }
            else {
              Enum v = Enum.valueOf(class0, String.valueOf(value));
              bw.setPropertyValue(name, v);
            }
          }

        }
        else
        {
          value = map.get(name);

          if (value != null)
          {
            bw.setPropertyValue(name, value);
          }
        }
      }
    }
    return (T) bw.getWrappedInstance();
  }

  public static <T> T map2BeanStrict(Map map, Class<T> clazz)
  {
    PropertyDescriptor[] arrayOfPropertyDescriptor1;
    BeanWrapper bw = new BeanWrapperImpl(clazz);
    PropertyDescriptor[] props = bw.getPropertyDescriptors();
    int j = (arrayOfPropertyDescriptor1 = props).length; for (int i = 0; i < j; ++i) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[i];
      String name = pd.getName();

      if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name)))
      {
        Object value;
        Class class0 = pd.getPropertyType();
        if (Enum.class.isAssignableFrom(class0))
        {
          value = map.get(name);
          if (value != null)
          {
            if (value.getClass() == class0) {
              bw.setPropertyValue(name, value);
            }
            else {
              Enum v = Enum.valueOf(class0, String.valueOf(value));
              bw.setPropertyValue(name, v);
            }
          }
        }
        else
        {
          value = map.get(name);
          if (value != null)
          {
            bw.setPropertyValue(name, value);
          }
        }
      }
    }
    return (T) bw.getWrappedInstance();
  }
  
	public static Map bean2MapStrict(Object beanObject) {
		BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
		PropertyDescriptor[] desc = bean.getPropertyDescriptors();
		Map dataMap = new HashMap(desc.length);
		try {
			for (int i = 0; i < desc.length; ++i) {
				String name = desc[i].getName();

				if ((!(bean.isWritableProperty(name)))
						|| (!(bean.isReadableProperty(name))))
					continue;
				Object object = bean.getPropertyValue(name);
				if (object == null) {
					continue;
				}
				dataMap.put(name, object);
			}

			return dataMap;
		} catch (Exception e1) {
          throw new RuntimeException(
                  "ngp.core.util.bean2bean_fail",
                  e1);
		}
	}
	public static List<Map> listBean2ListMapStrict(List list) {
		List result = new ArrayList();

		for (Iterator it = list.iterator(); it.hasNext();) {
			Object obj =it.next();
			if(obj instanceof Map){
				continue;
			}
			Map tmp = bean2MapStrict(obj);
			result.add(tmp);
		}
		return result;
	}
}

