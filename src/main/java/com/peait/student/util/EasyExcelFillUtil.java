package com.peait.student.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import org.apache.log4j.Logger;

/**
 * @author 朱永杰
 * 基于EasyExcel 的填充
 * <dependencies>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>easyexcel</artifactId>
 *             <version>2.2.3</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>log4j</groupId>
 *             <artifactId>log4j</artifactId>
 *             <version>1.2.17</version>
 *         </dependency>
 *     </dependencies>
 *     https://www.yuque.com/easyexcel/doc/fill
 */
public class EasyExcelFillUtil {
    /**日志*/
    private Logger log = Logger.getLogger(EasyExcelFillUtil.class.getClass());
    /**Excel写*/
    private ExcelWriter excelWriter;
    /**竖着填*/
    private  FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();

    /**
     * 构造器
     * @param templatePath
     *             模板位置
     * @param filePath
     *             导出文件位置
     */
    public EasyExcelFillUtil(String templatePath, String filePath) {
        try {
            if (null!=templatePath&&null!=filePath) {
                excelWriter = EasyExcel.write(filePath).withTemplate(templatePath).build();
            }
            else {
                log.error("请保证模板地址和文件地址不为空");
                throw new RuntimeException();
            }
        }
        catch (ExcelGenerateException ex)
        {
            log.error(ex.getCause());
            throw new RuntimeException();
        }

    }

    /**
     * 获取 WriteSheet
     * @return WriteSheet
     */
    public WriteSheet getWriteSheet() {
        return EasyExcel.writerSheet().build();
    }

    /**
     * 根据对象（Map）默认填充
     * @param data 对象/Map
     * @return 操作结果
     */
    public boolean fill(Object data){
        return this.fill(data,null);
    }

    /**
     * 列表默认填充
     * @param fillWrapper new FillWrapper(collectionData)  单列表组合填充
     *                    new FillWrapper(String name, Collection collectionData) 多列表组合填充
     * @return 操作结果
     */
    public boolean fill(FillWrapper fillWrapper){
        return this.fill(fillWrapper,null);
    }

    /**
     * 根据对象（Map）指定Sheet名填充
     * @param data 对象/Map
     * @return 操作结果
     */
    public boolean fill(Object data, String sheetName){
        WriteSheet writeSheet= EasyExcel.writerSheet().build();
        if (null!=sheetName) {
            writeSheet.setSheetName(sheetName);
        }
        return this.fillWithSheet(data,writeSheet);
    }

    /**
     * 列表根据FillWrapper 按照Sheet名填充
     * @param fillWrapper new FillWrapper(collectionData)  单列表组合填充
     *                    new FillWrapper(String name, Collection collectionData) 多列表组合填充
     * @return 操作结果
     */
    public boolean fill(FillWrapper fillWrapper,String sheetName){
        WriteSheet writeSheet= EasyExcel.writerSheet().build();
        if (null!=sheetName) {
            writeSheet.setSheetName(sheetName);
        }
        return this.fillWithSheet(fillWrapper,writeSheet);
    }

    /**
     * 列表根据FillWrapper 按照WriteSheet填充
     * @param fillWrapper new FillWrapper(collectionData)  单列表组合填充
     *                    new FillWrapper(String name, Collection collectionData) 多列表组合填充
     * @return 操作结果
     */
    public boolean fillWithSheet(FillWrapper fillWrapper,WriteSheet writeSheet){
        try {
            if (null!=fillWrapper) {
                excelWriter.fill(fillWrapper, writeSheet);
                log.info("你已经填充一组集合数据");
                return true;
            }
        }
        catch (Exception ex)
        {
            log.error(ex.getCause());
            return false;
        }
        return false;
    }

    /**
     * 根据对象（Map）指定Sheet名填充
     * @param data 对象/Map
     * @return 操作结果
     */
    public boolean fillWithSheet (Object data,WriteSheet writeSheet){
        try {
            excelWriter.fill(data, writeSheet);
            log.info("你已经填充一则数据");
            return true;
        }
        catch (Exception ex)
        {
            log.error(ex.getCause());
            return false;
        }
    }

    /**
     * 关闭excelWriter流
     */
    public void destroy(){
        if(null!=excelWriter){
            log.info("导出结束");
            try {
                excelWriter.finish();
            }
            catch (Exception ex)
            {
                log.info("关闭失败");
            }
        }
    }
}

