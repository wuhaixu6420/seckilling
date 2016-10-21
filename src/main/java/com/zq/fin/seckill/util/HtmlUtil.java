package com.zq.fin.seckill.util;


import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class HtmlUtil {  
    
    
    public static String delHTMLTag_Position(String htmlStr){ 
    	//script  table tbody  td
        String regEx_form="<form[^>]*?>";
        String regEx_form1="<\\/form>";
        String regEx_input="<input[^>]*?>";
        String regEx_script="<script[^>]*?>";
        String regEx_script1="<\\/script>";
        String regEx_table="<table[^>]*?>";
        String regEx_table1="<\\/table>";
        String regEx_tbody="<tbody[^>]*?>";
        String regEx_tbody1="<\\/tbody>";
        String regEx_td="<td[^>]*?>";
        String regEx_td1="<\\/td>";
        String regEx_div="<div[^>]*?>";
        String regEx_div1="<\\/div>";
        String regEx_nbsp="&nbsp;";
        String regEx_span="<span[^>]*?>";
        String regEx_span1="<\\/span>";
        String regEx_strong="<strong[^>]*?>";
        String regEx_strong1="<\\/strong>"; 
        String regEx_font="<font[^>]*?>";
        String regEx_font1="<\\/font>";
        String regEx_tr="<tr[^>]*?>";
        String regEx_tr1="<\\/tr>";
        
        Pattern p_tr=Pattern.compile(regEx_tr,Pattern.CASE_INSENSITIVE); 
        Matcher m_tr=p_tr.matcher(htmlStr); 
        htmlStr=m_tr.replaceAll("abc"); //过滤strong标签
        
        Pattern p_tr1=Pattern.compile(regEx_tr1,Pattern.CASE_INSENSITIVE); 
        Matcher m_tr1=p_tr1.matcher(htmlStr); 
        htmlStr=m_tr1.replaceAll(""); //过滤strong标签
        
        Pattern p_font=Pattern.compile(regEx_font,Pattern.CASE_INSENSITIVE); 
        Matcher m_font=p_font.matcher(htmlStr); 
        htmlStr=m_font.replaceAll(""); //过滤strong标签
        
        Pattern p_font1=Pattern.compile(regEx_font1,Pattern.CASE_INSENSITIVE); 
        Matcher m_font1=p_font1.matcher(htmlStr); 
        htmlStr=m_font1.replaceAll(""); //过滤strong标签
        
        Pattern p_strong=Pattern.compile(regEx_strong,Pattern.CASE_INSENSITIVE); 
        Matcher m_strong=p_strong.matcher(htmlStr); 
        htmlStr=m_strong.replaceAll(""); //过滤strong标签
        
        Pattern p_strong1=Pattern.compile(regEx_strong1,Pattern.CASE_INSENSITIVE); 
        Matcher m_strong1=p_strong1.matcher(htmlStr); 
        htmlStr=m_strong1.replaceAll(""); //过滤strong标签
        
        Pattern p_span=Pattern.compile(regEx_span,Pattern.CASE_INSENSITIVE); 
        Matcher m_span=p_span.matcher(htmlStr); 
        htmlStr=m_span.replaceAll(""); //过滤span标签
        
        Pattern p_span1=Pattern.compile(regEx_span1,Pattern.CASE_INSENSITIVE); 
        Matcher m_span1=p_span1.matcher(htmlStr); 
        htmlStr=m_span1.replaceAll(""); //过滤span标签
        
        
        Pattern p_nbsp=Pattern.compile(regEx_nbsp,Pattern.CASE_INSENSITIVE); 
        Matcher m_nbsp=p_nbsp.matcher(htmlStr); 
        htmlStr=m_nbsp.replaceAll(""); //过滤nbsp标签 
        
        Pattern p_div=Pattern.compile(regEx_div,Pattern.CASE_INSENSITIVE); 
        Matcher m_div=p_div.matcher(htmlStr); 
        htmlStr=m_div.replaceAll("");
        
        Pattern p_div1=Pattern.compile(regEx_div1,Pattern.CASE_INSENSITIVE); 
        Matcher m_div1=p_div1.matcher(htmlStr); 
        htmlStr=m_div1.replaceAll(""); 
        
        Pattern p_td=Pattern.compile(regEx_td,Pattern.CASE_INSENSITIVE); 
        Matcher m_td=p_td.matcher(htmlStr); 
        htmlStr=m_td.replaceAll("");
        
        Pattern p_td1=Pattern.compile(regEx_td1,Pattern.CASE_INSENSITIVE); 
        Matcher m_td1=p_td1.matcher(htmlStr); 
        htmlStr=m_td1.replaceAll("stock"); 
        
        Pattern p_tbody=Pattern.compile(regEx_tbody,Pattern.CASE_INSENSITIVE); 
        Matcher m_tbody=p_tbody.matcher(htmlStr); 
        htmlStr=m_tbody.replaceAll("");
        
        Pattern p_tbody1=Pattern.compile(regEx_tbody1,Pattern.CASE_INSENSITIVE); 
        Matcher m_tbody1=p_tbody1.matcher(htmlStr); 
        htmlStr=m_tbody1.replaceAll(""); 
        
        Pattern p_table=Pattern.compile(regEx_table,Pattern.CASE_INSENSITIVE); 
        Matcher m_table=p_table.matcher(htmlStr); 
        htmlStr=m_table.replaceAll("");
        
        Pattern p_table1=Pattern.compile(regEx_table1,Pattern.CASE_INSENSITIVE); 
        Matcher m_table1=p_table1.matcher(htmlStr); 
        htmlStr=m_table1.replaceAll(""); 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll("");
        
        Pattern p_script1=Pattern.compile(regEx_script1,Pattern.CASE_INSENSITIVE); 
        Matcher m_script1=p_script1.matcher(htmlStr); 
        htmlStr=m_script1.replaceAll(""); 
        
        Pattern p_input=Pattern.compile(regEx_input,Pattern.CASE_INSENSITIVE); 
        Matcher m_input=p_input.matcher(htmlStr); 
        htmlStr=m_input.replaceAll("");
        
        Pattern p_form=Pattern.compile(regEx_form,Pattern.CASE_INSENSITIVE); 
        Matcher m_form=p_form.matcher(htmlStr); 
        htmlStr=m_form.replaceAll("");
        
        Pattern p_form1=Pattern.compile(regEx_form1,Pattern.CASE_INSENSITIVE); 
        Matcher m_form1=p_form1.matcher(htmlStr); 
        htmlStr=m_form1.replaceAll(""); 
        
        return htmlStr.trim(); //返回文本字符串 
    } 
    
    public static String delHTMLTag_Asset(String htmlStr){ 
    	//script  table tbody  td
        String regEx_form="<form[^>]*?>";
        String regEx_form1="<\\/form>";
        String regEx_input="<input[^>]*?>";
        String regEx_script="<script[^>]*?>";
        String regEx_script1="<\\/script>";
        String regEx_table="<table[^>]*?>";
        String regEx_table1="<\\/table>";
        String regEx_tbody="<tbody[^>]*?>";
        String regEx_tbody1="<\\/tbody>";
        String regEx_td="<td[^>]*?>";
        String regEx_td1="<\\/td>";
        String regEx_div="<div[^>]*?>";
        String regEx_div1="<\\/div>";
        String regEx_nbsp="&nbsp;";
        String regEx_span="<span[^>]*?>";
        String regEx_span1="<\\/span>";
        String regEx_strong="<strong[^>]*?>";
        String regEx_strong1="<\\/strong>"; 
        String regEx_font="<font[^>]*?>";
        String regEx_font1="<\\/font>";
        String regEx_tr="<tr[^>]*?>";
        String regEx_tr1="<\\/tr>";
        String regEx_br="<br[^>]*?>";
        
        Pattern p_br=Pattern.compile(regEx_br,Pattern.CASE_INSENSITIVE); 
        Matcher m_br=p_br.matcher(htmlStr); 
        htmlStr=m_br.replaceAll("abc"); //过滤strong标签
        
        Pattern p_tr=Pattern.compile(regEx_tr,Pattern.CASE_INSENSITIVE); 
        Matcher m_tr=p_tr.matcher(htmlStr); 
        htmlStr=m_tr.replaceAll("abc"); //过滤strong标签
        
        Pattern p_tr1=Pattern.compile(regEx_tr1,Pattern.CASE_INSENSITIVE); 
        Matcher m_tr1=p_tr1.matcher(htmlStr); 
        htmlStr=m_tr1.replaceAll(""); //过滤strong标签
        
        Pattern p_font=Pattern.compile(regEx_font,Pattern.CASE_INSENSITIVE); 
        Matcher m_font=p_font.matcher(htmlStr); 
        htmlStr=m_font.replaceAll(""); //过滤strong标签
        
        Pattern p_font1=Pattern.compile(regEx_font1,Pattern.CASE_INSENSITIVE); 
        Matcher m_font1=p_font1.matcher(htmlStr); 
        htmlStr=m_font1.replaceAll(""); //过滤strong标签
        
        Pattern p_strong=Pattern.compile(regEx_strong,Pattern.CASE_INSENSITIVE); 
        Matcher m_strong=p_strong.matcher(htmlStr); 
        htmlStr=m_strong.replaceAll(""); //过滤strong标签
        
        Pattern p_strong1=Pattern.compile(regEx_strong1,Pattern.CASE_INSENSITIVE); 
        Matcher m_strong1=p_strong1.matcher(htmlStr); 
        htmlStr=m_strong1.replaceAll(""); //过滤strong标签
        
        Pattern p_span=Pattern.compile(regEx_span,Pattern.CASE_INSENSITIVE); 
        Matcher m_span=p_span.matcher(htmlStr); 
        htmlStr=m_span.replaceAll(""); //过滤span标签
        
        Pattern p_span1=Pattern.compile(regEx_span1,Pattern.CASE_INSENSITIVE); 
        Matcher m_span1=p_span1.matcher(htmlStr); 
        htmlStr=m_span1.replaceAll(""); //过滤span标签
        
        
        Pattern p_nbsp=Pattern.compile(regEx_nbsp,Pattern.CASE_INSENSITIVE); 
        Matcher m_nbsp=p_nbsp.matcher(htmlStr); 
        htmlStr=m_nbsp.replaceAll(""); //过滤nbsp标签 
        
        Pattern p_div=Pattern.compile(regEx_div,Pattern.CASE_INSENSITIVE); 
        Matcher m_div=p_div.matcher(htmlStr); 
        htmlStr=m_div.replaceAll("");
        
        Pattern p_div1=Pattern.compile(regEx_div1,Pattern.CASE_INSENSITIVE); 
        Matcher m_div1=p_div1.matcher(htmlStr); 
        htmlStr=m_div1.replaceAll(""); 
        
        Pattern p_td=Pattern.compile(regEx_td,Pattern.CASE_INSENSITIVE); 
        Matcher m_td=p_td.matcher(htmlStr); 
        htmlStr=m_td.replaceAll("");
        
        Pattern p_td1=Pattern.compile(regEx_td1,Pattern.CASE_INSENSITIVE); 
        Matcher m_td1=p_td1.matcher(htmlStr); 
        htmlStr=m_td1.replaceAll("stock"); 
        
        Pattern p_tbody=Pattern.compile(regEx_tbody,Pattern.CASE_INSENSITIVE); 
        Matcher m_tbody=p_tbody.matcher(htmlStr); 
        htmlStr=m_tbody.replaceAll("");
        
        Pattern p_tbody1=Pattern.compile(regEx_tbody1,Pattern.CASE_INSENSITIVE); 
        Matcher m_tbody1=p_tbody1.matcher(htmlStr); 
        htmlStr=m_tbody1.replaceAll(""); 
        
        Pattern p_table=Pattern.compile(regEx_table,Pattern.CASE_INSENSITIVE); 
        Matcher m_table=p_table.matcher(htmlStr); 
        htmlStr=m_table.replaceAll("");
        
        Pattern p_table1=Pattern.compile(regEx_table1,Pattern.CASE_INSENSITIVE); 
        Matcher m_table1=p_table1.matcher(htmlStr); 
        htmlStr=m_table1.replaceAll(""); 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll("");
        
        Pattern p_script1=Pattern.compile(regEx_script1,Pattern.CASE_INSENSITIVE); 
        Matcher m_script1=p_script1.matcher(htmlStr); 
        htmlStr=m_script1.replaceAll(""); 
        
        Pattern p_input=Pattern.compile(regEx_input,Pattern.CASE_INSENSITIVE); 
        Matcher m_input=p_input.matcher(htmlStr); 
        htmlStr=m_input.replaceAll("");
        
        Pattern p_form=Pattern.compile(regEx_form,Pattern.CASE_INSENSITIVE); 
        Matcher m_form=p_form.matcher(htmlStr); 
        htmlStr=m_form.replaceAll("");
        
        Pattern p_form1=Pattern.compile(regEx_form1,Pattern.CASE_INSENSITIVE); 
        Matcher m_form1=p_form1.matcher(htmlStr); 
        htmlStr=m_form1.replaceAll(""); 
        
        return htmlStr.trim(); //返回文本字符串 
    }

}