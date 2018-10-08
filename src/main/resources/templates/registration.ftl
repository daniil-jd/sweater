<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
Add new user:
${message}
<@l.login "/registration" />
</@c.page>
