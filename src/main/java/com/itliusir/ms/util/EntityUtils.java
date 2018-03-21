package com.itliusir.ms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类相关工具类
 *
 * @author liugang
 * @since 2018-03-06
 */
public class EntityUtils {


    public static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for(int i=0;i<fields.length;i++){
            String field = fields[i];
            if(ReflectionUtils.hasField(entity, field)){
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    /**
     * 快速将bean的fcreateTime、flastEditTime、fcreatorId、flastEditorId附上相关值
     *
     * @author liugang 2017-12-23 16:36
     * */
    public static <T> void setBizCreatAndUpdatInfo(T entity) {
        setBizCreateInfo(entity);
        setBizUpdatedInfo(entity);
    }

    /**
     * 快速将bean的fcreateTime、fcreatorId附上相关值
     *
     * @author liugang 2017-12-23 16:38
     * */
    public static <T> void setBizCreateInfo(T entity){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int id = 0;
        if(request!=null&&request.getHeader("userId")!=null) {
            id = Integer.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"creatorId","createTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "createTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{id,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的flastEditTime、flastEditorId附上相关值
     *
     * @author liugang 2017-12-23 16:46
     * */
    public static <T> void setBizUpdatedInfo(T entity){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        int id = 0;
        if(request!=null&&request.getHeader("userId")!=null) {
            id = Integer.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"lastEditorId","lastEditTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "lastEditTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{id,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的fdeleteTime、fdeletorId附上相关值
     *
     * @author liugang 2017-12-23 16:46
     * */
    public static <T> void setBizLogicDeleteInfo(T entity){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        int id = 0;
        if(request!=null&&request.getHeader("userId")!=null) {
            id = Integer.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"deletorId","deleteTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "deleteTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{id,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的fcheckerId、fcheckerTime附上相关值
     * @Author: HuoZhiQiang
     * @Date: 2018/1/9 14:32
     */
    public static <T> void setBizUpdateCheckedInfo(T entity){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        int id = 0;
        if(request != null && request.getHeader("userId") != null) {
            id = Integer.valueOf(request.getHeader("userId"));
        }
        // 默认属性
        String[] fields = {"checkerId","checkerTime"};

        Field field = ReflectionUtils.getAccessibleField(entity, "checkerTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{id,new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 获取对象中为null的属性名
     *
     * @author liugang 2018-03-08 13:45
     * */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝非空对象属性值
     *
     * @author liugang 2018-03-08 13:46
     * */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
