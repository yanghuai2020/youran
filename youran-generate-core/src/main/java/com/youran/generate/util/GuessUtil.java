package com.youran.generate.util;

import com.youran.common.util.DateUtil;
import com.youran.generate.constant.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Date;

/**
 * 猜测工具类
 * <p> 用来智能猜测元数据字段
 *
 * @author cbb
 * @date 2019/3/15
 */
public class GuessUtil {


    public static final String[] CREATE_PREFIX = {"create", "created"};
    public static final String[] OPERATE_PREFIX = {"operate", "operated", "update", "updated", "modify", "modified"};
    public static final String[] TIME_SUFFIX = {"time", "date", "at"};
    public static final String[] USER_SUFFIX = {"by", "user", "er", "or"};
    public static final String[] DELETED_LABEL = {"deleted", "delete_status", "is_delete", "is_deleted", "is_del"};
    public static final String VERSION_LABEL = "version";
    public static final String NAME_LABEL = "name";


    /**
     * 根据mysql字段类型猜测默认值
     *
     * @param fieldType
     * @return
     */
    public static String guessDefaultValueByFieldType(String fieldType) {
        if (MySqlType.isStringType(fieldType)) {
            return "";
        }
        if (MySqlType.isNumberType(fieldType)) {
            return "0";
        }
        if (MySqlType.isTimestampType(fieldType)) {
            return "0";
        }
        if (MySqlType.isDateType(fieldType)) {
            return "1900-01-01";
        }
        if (MySqlType.isDateTimeType(fieldType)) {
            return "1900-01-01 00:00:00";
        }
        return "";
    }

    /**
     * 根据java字段类型和长度猜测queryType
     *
     * @param jFieldType java字段类型
     * @param length     长度
     * @return
     */
    public static int guessQueryType(JFieldType jFieldType, int length) {
        if (jFieldType == JFieldType.STRING) {
            if (length > QueryType.LIKE_LENGTH_THRESHOLD) {
                return QueryType.LIKE;
            } else {
                return QueryType.EQ;
            }
        } else if (jFieldType == JFieldType.INTEGER) {
            return QueryType.EQ;
        } else if (jFieldType == JFieldType.SHORT) {
            return QueryType.EQ;
        } else if (jFieldType == JFieldType.LONG) {
            return QueryType.EQ;
        } else if (jFieldType == JFieldType.FLOAT) {
            return QueryType.BETWEEN;
        } else if (jFieldType == JFieldType.DOUBLE) {
            return QueryType.BETWEEN;
        } else if (jFieldType == JFieldType.BIGDECIMAL) {
            return QueryType.BETWEEN;
        } else if (jFieldType == JFieldType.DATE) {
            return QueryType.BETWEEN;
        }
        return QueryType.EQ;
    }

    /**
     * 判断字段名是否匹配规则
     *
     * @param fieldNameLowerCase 小写的字段名
     * @param labels             规则数组
     * @param prefix             前缀还是后缀
     * @return
     */
    private static boolean isMatchLabel(String fieldNameLowerCase, String[] labels, boolean prefix) {
        for (String label : labels) {
            if (prefix) {
                if (fieldNameLowerCase.startsWith(label)) {
                    return true;
                }
            } else {
                if (fieldNameLowerCase.endsWith(label)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 猜测java字段类型
     *
     * @param fieldName   mysql字段名
     * @param mySqlType   mysql字段类型
     * @param fieldLength 字段长度
     * @return
     */
    public static final JFieldType guessJFieldType(String fieldName, String mySqlType, int fieldLength) {
        JFieldType jFieldType;
        if (MySqlType.VARCHAR.equals(mySqlType)) {
            jFieldType = JFieldType.STRING;
        } else if (MySqlType.TEXT.equals(mySqlType)) {
            jFieldType = JFieldType.STRING;
        } else if (MySqlType.DATE.equals(mySqlType)) {
            jFieldType = JFieldType.DATE;
        } else if (MySqlType.DATETIME.equals(mySqlType)) {
            jFieldType = JFieldType.DATE;
        } else if (MySqlType.FLOAT.equals(mySqlType)) {
            jFieldType = JFieldType.FLOAT;
        } else if (MySqlType.DOUBLE.equals(mySqlType)) {
            jFieldType = JFieldType.DOUBLE;
        } else if (MySqlType.DECIMAL.equals(mySqlType)) {
            jFieldType = JFieldType.BIGDECIMAL;
        } else if (MySqlType.BIGINT.equals(mySqlType)) {
            jFieldType = JFieldType.LONG;
        } else if (MySqlType.TIMESTAMP.equals(mySqlType)) {
            jFieldType = JFieldType.DATE;
        } else if (MySqlType.CHAR.equals(mySqlType)) {
            jFieldType = JFieldType.STRING;
        } else if (MySqlType.SMALLINT.equals(mySqlType)) {
            jFieldType = JFieldType.INTEGER;
        } else if (MySqlType.MEDIUMINT.equals(mySqlType)) {
            jFieldType = JFieldType.INTEGER;
        } else if (MySqlType.TINYINT.equals(mySqlType)) {
            if (fieldLength == 1) {
                jFieldType = JFieldType.BOOLEAN;
            } else {
                jFieldType = JFieldType.INTEGER;
            }
        } else if (MySqlType.INT.equals(mySqlType)) {
            jFieldType = JFieldType.INTEGER;
        } else {
            jFieldType = JFieldType.STRING;
        }
        /**
         * 修正deleted字段的类型为boolean
         */
        if (jFieldType == JFieldType.INTEGER && ArrayUtils.contains(DELETED_LABEL, fieldName.toLowerCase())) {
            jFieldType = JFieldType.BOOLEAN;
        }
        return jFieldType;
    }


    /**
     * 猜测特殊字段类型
     *
     * @param fieldName
     * @param jFieldType
     * @return
     */
    public static String guessSpecialField(String fieldName, JFieldType jFieldType) {
        String lowerCase = fieldName.toLowerCase();
        boolean createPre = isMatchLabel(lowerCase, CREATE_PREFIX, true);
        boolean operatePre = isMatchLabel(lowerCase, OPERATE_PREFIX, true);
        if (jFieldType == JFieldType.DATE) {
            boolean timeSuff = isMatchLabel(lowerCase, TIME_SUFFIX, false);
            if (createPre && timeSuff) {
                return MetaSpecialField.CREATED_TIME;
            }
            if (operatePre && timeSuff) {
                return MetaSpecialField.OPERATED_TIME;
            }
        }
        if (jFieldType == JFieldType.STRING) {
            boolean userSuff = isMatchLabel(lowerCase, USER_SUFFIX, false);
            if (createPre && userSuff) {
                return MetaSpecialField.CREATED_BY;
            }
            if (operatePre && userSuff) {
                return MetaSpecialField.OPERATED_BY;
            }
        }
        if (VERSION_LABEL.equals(lowerCase) && jFieldType == JFieldType.INTEGER) {
            return MetaSpecialField.VERSION;
        }
        if (ArrayUtils.contains(DELETED_LABEL, lowerCase) && jFieldType == JFieldType.BOOLEAN) {
            return MetaSpecialField.DELETED;
        }
        return null;
    }

    /**
     * 猜测字段示例值
     *
     * @param fieldName
     * @param jFieldType
     * @param fieldLength
     * @return
     */
    public static String guessFieldExample(String fieldName, JFieldType jFieldType, int fieldLength) {
        String lowerCase = fieldName.toLowerCase();
        if (jFieldType == JFieldType.STRING) {
            if (lowerCase.endsWith(NAME_LABEL)) {
                if (fieldLength >= 5) {
                    return "name1";
                } else {
                    return "X";
                }
            }

        }
        if (jFieldType == JFieldType.INTEGER || jFieldType == JFieldType.LONG || jFieldType == JFieldType.SHORT || jFieldType == JFieldType.FLOAT || jFieldType == JFieldType.DOUBLE || jFieldType == JFieldType.BIGDECIMAL) {
            return "1";
        }
        if (jFieldType == JFieldType.BOOLEAN) {
            return "true";
        }
        if (jFieldType == JFieldType.LOCALDATE) {
            return DateUtil.getDateStr(new Date(), DateUtil.DATE_FORMAT_1);
        }
        if (jFieldType == JFieldType.LOCALDATETIME) {
            return DateUtil.getDateStr(new Date(), DateUtil.DATE_FORMAT_2);
        }
        if (jFieldType == JFieldType.DATE) {
            return DateUtil.getDateStr(new Date(), DateUtil.DATE_FORMAT_2);
        }
        return "";
    }

    /**
     * 根据字段类型猜测常量类型
     *
     * @param jFieldType
     * @return
     */
    public static Integer guessConstType(JFieldType jFieldType) {
        if (jFieldType == JFieldType.INTEGER || jFieldType == JFieldType.SHORT) {
            return MetaConstType.INTEGER;
        }
        return MetaConstType.STRING;
    }

    /**
     * 根据字段类型猜测编辑框类型
     *
     * @param jFieldType
     * @param fieldType
     * @return
     */
    public static Integer guessEditType(JFieldType jFieldType, String fieldType) {
        if (jFieldType.equals(JFieldType.STRING)) {
            if (MySqlType.TEXT.equals(fieldType)) {
                return EditType.TEXTAREA.getValue();
            } else {
                return EditType.TEXT.getValue();
            }
        }
        if (jFieldType.equals(JFieldType.BOOLEAN)) {
            return EditType.RADIO.getValue();
        }
        if (jFieldType.equals(JFieldType.DATE)) {
            if (MySqlType.DATE.equals(fieldType)) {
                return EditType.DATE.getValue();
            } else {
                return EditType.DATETIME.getValue();
            }
        }
        return EditType.NUMBER.getValue();
    }

    /**
     * 猜测主键策略
     *
     * @param fieldType
     * @param fieldLength
     * @param autoIncrement
     * @return
     */
    public static PrimaryKeyStrategy guessPkStrategy(String fieldType, int fieldLength, boolean autoIncrement) {
        if (autoIncrement) {
            return PrimaryKeyStrategy.AUTO_INCREMENT;
        }
        if (MySqlType.VARCHAR.equals(fieldType)) {
            if (fieldLength == 32) {
                return PrimaryKeyStrategy.UUID_32;
            } else if (fieldLength == 16) {
                return PrimaryKeyStrategy.UUID_16;
            }
        }
        return PrimaryKeyStrategy.NONE;
    }


}
