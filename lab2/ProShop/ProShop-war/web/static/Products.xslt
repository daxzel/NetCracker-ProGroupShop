<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="1.xml" -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
   
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Документ без названия</title>
        </head>
        
        <body style="background-color:#999">
        	<xsl:apply-templates select="PRODUCTS"/>
        </body>
        </html>
    </xsl:template>
    
	<xsl:template match="PRODUCTS">
    	<div style=" padding-left:100px; width:1000px; border-color:#666">
        	<xsl:apply-templates select="PRODUCT"/>  
        </div>     
 	</xsl:template>
    
    <xsl:template match="PRODUCT">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
        	<div>
            	<div style="border-bottom-style:double; border-width:2px;">
        			<h4><xsl:value-of select="./NAME"/> (<xsl:value-of select="./attribute::ID"/>) </h4>
                </div>
            	<p><xsl:value-of select="./DESCRIPTION"/> </p>
            </div>
            <div style="text-align:right">
            	<p><strong>Цена: </strong><xsl:value-of select="./PRICE"/> </p>	
            </div>
        </div>   
    </xsl:template>  
    
</xsl:stylesheet>