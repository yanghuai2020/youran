${person.id}
${person.name}
<#assign xxx>
    <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
        ${n}
    </#list>
</#assign>
${xxx}

<#assign linkman="hello">
word:${linkman}

<#assign info={"mobile":"13188886666","address":"北京市朝阳区"} >
电话:${info.mobile} 地址:${info.address}

==========================================================1=============================================================================
<#macro mcdemo>
    <h1><#nested></h1>
</#macro>

<@mcdemo>
    hello world
</@mcdemo>

==========================================================2=============================================================================
<#macro repeat count>
    <#local y='test'>
    <#list 1.. count as x>
        ${y!'NN'} ${count}/${x}:<#nested>
    </#list>
</#macro>
<@repeat count=3>${y!'N'}   ${x!'N'}     ${count!'N'}</@repeat>

===========================================================3============================================================================

<#macro repeat1 count>
    <#list 1..count as x>
        <#nested x, x/2, x==count>
    </#list>
</#macro>
<@repeat1 count=4 ; c, halfc, last>
    ${c}. ${halfc}<#if last> Last!</#if>
</@repeat1>

===========================================================4============================================================================
<@repeat1 count=4 ; c, halfc, last>
    ${c}. ${halfc}<#if last> Last!</#if>
</@repeat1>

<@repeat1 count=4 ; c, halfc>
    ${c}. ${halfc}
</@repeat1>

<@repeat1 count=4>
    Just repeat it...
</@repeat1>

===========================================================5============================================================================





