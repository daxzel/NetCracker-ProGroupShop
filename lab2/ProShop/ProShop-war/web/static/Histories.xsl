<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
	
	<xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Вывод истории изменений</title>
        </head>
        
        <body style="background-color:#999">
        	<xsl:apply-templates select="HISTORIES"/>
        </body>
        </html>
    </xsl:template>
	
		<xsl:template match="HISTORIES">
    	<div style=" padding-left:100px; width:1000px; border-color:#666">
			
			<p><strong>История изменений</strong></p>
			<xsl:apply-templates select="HISTORY"/>
						
        </div>     
 	</xsl:template>
	
		<xsl:template match="HISTORY">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
			<div style="text-align:left">            		
            </div>
        	   	<div>
					 <p><strong>Ид изменения:  </strong><xsl:value-of select="./attribute::ID_HISTORY"/> </p>
        			 <p><strong>Автор изменения:   </strong>  <xsl:value-of select="./NIK_USER"/></p>  
					 <p><strong>Измененная таблица : </strong> <xsl:value-of select="./NAME_TABLE"/> </p>
					 <p><strong>Изменение : </strong> <xsl:value-of select="./STATUS"/> </p>
					 <p><strong>Дата изменения : </strong> <xsl:value-of select="./DATE_UPDATE"/> </p>
					 <p><strong>Объект изменения : </strong> <xsl:value-of select="./ID_OBJECT"/> </p>					 
                </div>
        </div>   
    </xsl:template> 
	
</xsl:stylesheet>