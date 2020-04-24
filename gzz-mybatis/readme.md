
##使用注解方式时，需要在SqlProvider文件中的 selectByExample方法中，手工加以下代码
 if (example != null && example.getStartRow() != null) {
    sql.LIMIT(example.getStartRow()).OFFSET(example.getPageSize());
 }