/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.haad.service.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 *
 * @author smutlak
 */
public class XmlDateTimeAdapter extends XmlAdapter<String, Date> {
    @Override
    public String marshal(Date v) throws Exception {
        //DateUtils.truncate
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(v);
        //String s = dateFormat.format(v);
        //return s;
    }

    @Override
    public Date unmarshal(String v) throws Exception {
       // Date ret =  dateFormat.parse(v);
       // if(ret == null)
       // return ret;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.parse(v);
    }
}
