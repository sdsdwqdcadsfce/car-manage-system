package com.peait.student.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * excel导出工具类 使用方法
 * 1.在本地或者文档库配置模板
 * 2.使用该类进行导出到本地或者浏览器下载
 */
public class EasyExcelExportUtil {

    /**
     * 单个sheet页面 带原有样式   单个数据集 基础版本 可以包含单一数据 不需要可以为空  本地到本地的导出方式
     * 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
     *  // {} 代表普通变量 {.} 代表是list的变量
     * @param templateFilePath    本地模板的地址
     * @param destinationFilePath 目标源地址
     * @param dataListMap         数据列表集   Map<String,List<T>>> 该sheet下的数据集  模板配置的{.xx}
     * @param dataMap             数据集非列表形式    Map<String, Object>>该sheet下的数据  模板配置的{xx}
     */
    public static <T> T LocalToLocalExcelExprotOneSheetBase(String templateFilePath, String destinationFilePath, List<T> dataListMap, Map<String, Object> dataMap) {
        //配置数据源和目标地
        ExcelWriter excelWriter = EasyExcel.write(destinationFilePath).withTemplate(templateFilePath).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        //配置数据
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        if (dataMap != null && !dataMap.isEmpty()) {
            excelWriter.fill(dataMap, writeSheet);
        }

        //配置数据列表
        if(dataListMap!=null && !dataListMap.isEmpty()){
            excelWriter.fill(dataListMap,fillConfig, writeSheet);
        }
        //关流
        excelWriter.finish();
        return null;
    }

    /**
     * 单个sheet页面  有1到多个数据集 并且包含单一数据 本地到本地的导出方式 带原有样式
     * 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
     * // {} 代表普通变量 {.} 代表是list的变量
     * @param templateFilePath    本地模板的地址
     * @param destinationFilePath 目标源地址
     * @param dataListMap         数据列表集     Map<String,List<T>>> 该sheet下的数据集  模板配置的{key.xx}
     * @param dataMap             数据集非列表形式    Map<String, Object>>该sheet下的数据  模板配置的{xx}
     */
    public static <T> T LocalToLocalExcelExprotOneSheet(String templateFilePath, String destinationFilePath, Map<String, List<T>> dataListMap, Map<String, Object> dataMap) {
        //配置数据源和目标地
        ExcelWriter excelWriter = EasyExcel.write(destinationFilePath).withTemplate(templateFilePath).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        //配置数据列表
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        if (dataListMap != null && !dataListMap.isEmpty()) {
            Set<Map.Entry<String, List<T>>> entries = dataListMap.entrySet();
            for (Map.Entry<String, List<T>> map : entries) {
                String key = map.getKey();
                List<T> value = map.getValue();
                try {
                    excelWriter.fill(new FillWrapper(key, value), fillConfig, writeSheet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //配置数据
        if (dataMap != null && !dataMap.isEmpty()) {
            excelWriter.fill(dataMap, writeSheet);
        }
        //关流
        excelWriter.finish();
        return null;
    }

    /**
     * 本地导出本地的 多个sheet 以及复杂多列表组合填充填充 带原有样式
     *  模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
     *   // {} 代表普通变量 {.} 代表是list的变量
     * @param templateFilePath    本地模板的地址
     * @param destinationFilePath 目标源地址
     * @param dataListMap         数据列表集  Integer sheet页码 0开始   Map<String,List<T>>> 该sheet下的数据集  模板配置的{key.xx}
     * @param dataMap             数据集非列表形式  Integer sheet页码 0开始    Map<String, Object>>该sheet下的数据  模板配置的{xx}
     */
    public static <T> T LocalToLocalExcelExprotMoreSheet(String templateFilePath, String destinationFilePath, Map<Integer, Map<String, List<T>>> dataListMap, Map<Integer, Map<String, Object>> dataMap) {
        //配置数据源和目标地
        ExcelWriter excelWriter = EasyExcel.write(destinationFilePath).withTemplate(templateFilePath).build();
        //配置数据列表
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        if (dataListMap != null && !dataListMap.isEmpty()) {
            Set<Map.Entry<Integer, Map<String, List<T>>>> entries = dataListMap.entrySet();
            for (Map.Entry<Integer, Map<String, List<T>>> map : entries) {
                Integer key = map.getKey();
                Map<String, List<T>> value = map.getValue();
                try {
                    Set<Map.Entry<String, List<T>>> entries1 = value.entrySet();
                    for (Map.Entry<String, List<T>> entry : entries1) {
                        String key1 = entry.getKey();
                        List<T> value1 = entry.getValue();
                        WriteSheet writeSheet = EasyExcel.writerSheet(key).build();
                        excelWriter.fill(new FillWrapper(key1, value1), fillConfig, writeSheet);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //配置数据
        if (dataMap != null && !dataMap.isEmpty()) {
            Set<Map.Entry<Integer, Map<String, Object>>> entries = dataMap.entrySet();
            for (Map.Entry<Integer, Map<String, Object>> map : entries) {
                Integer key = map.getKey();
                Map<String, Object> value = map.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(key).build();
                excelWriter.fill(value, writeSheet);
            }
        }
        //关流
        excelWriter.finish();
        return null;
    }
}
