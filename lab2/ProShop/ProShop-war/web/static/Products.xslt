<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="1.xml" -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
   
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Документ без названия</title>
        </head>
        
        <body>
        	<xsl:apply-templates select="PRODUCTS"/>
        </body>
        </html>
    </xsl:template>
    
	<xsl:template match="PRODUCTS">
    	<div style=" padding-left:100px; width:1000px">
        	<xsl:apply-templates select="PRODUCT"/>  
        </div>     
 	</xsl:template>
    
    <xsl:template match="PRODUCT">  
    	<div style="background-color:#0FF">
        	<h5><xsl:value-of select="./NAME"/> (<xsl:value-of select="./attribute::ID"/>) </h5>
            <p><xsl:value-of select="./DESCRIPTION"/> </p>
        </div>   
    </xsl:template>  
    
</xsl:stylesheet>