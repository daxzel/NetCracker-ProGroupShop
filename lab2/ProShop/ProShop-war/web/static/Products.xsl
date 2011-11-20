<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="1.xml" -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
   
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Просмотр XML документа</title>
        </head>
        
        <body style="background-color:#999">
        	<xsl:apply-templates select="BASE"/>
        </body>
        </html>
    </xsl:template>
    
	<xsl:template match="BASE">
    	<div style=" padding-left:100px; width:1000px; border-color:#666">
			
			<xsl:apply-templates select="ROLE"/>
			
			<xsl:apply-templates select="USER"/>
			
			<xsl:apply-templates select="CATALOG"/>
			
        	<xsl:apply-templates select="PRODUCT"/> 
			
			<xsl:apply-templates select="OPINION"/>  			
			
			<xsl:apply-templates select="ORDER"/>  
        </div>     
 	</xsl:template>

	<xsl:template match="ROLE">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
			<div style="text-align:left">
            	<p><strong>Роль</strong></p>	
            </div>
        	   	<div>
        			 <p><strong>Название роли:   </strong>  <xsl:value-of select="./NAME"/></p>  <p><strong> Ид роли : </strong> <xsl:value-of select="./attribute::ID_ROLE"/> </p>
                </div>
        </div>   
    </xsl:template>  
	
	<xsl:template match="USER">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
        	<div>
				<div style="text-align:left">
					<p><strong>Пользователь</strong></p>	
				</div>
            	<div style="border-bottom-style:double; border-width:2px;">
        			<h4><xsl:value-of select="./NIK"/> (<xsl:value-of select="./attribute::ID_USER"/>) </h4>
					
                </div>
            	<p>
					<p><strong>Фамилия:    </strong><xsl:value-of select="./SURNAME"/></p>
				    <p><strong>Имя:    </strong><xsl:value-of select="./NAME"/> </p>
					<p><strong>Отчество:   </strong><xsl:value-of select="./OTCHESTVO"/></p> 
					<p><strong>Дата рождения:    </strong><xsl:value-of select="./BORN"/></p> 
					<p><strong>Телефон:  </strong><xsl:value-of select="./PHONE"/></p>
					<p><strong>Электронный адресс:  </strong><xsl:value-of select="./EMAIL"/></p>
					<p><strong>Ид роли:  </strong><xsl:value-of select="./ID_ROLE"/></p>					
				</p>
            </div>
            
        </div>   
    </xsl:template>  
	
	<xsl:template match="CATALOG">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
        	<div>
				<div style="text-align:left">
					<p><strong>Каталог</strong></p>	
				</div>
            	<div style="border-bottom-style:double; border-width:2px;">
        			<h4><xsl:value-of select="./NAME"/> (<xsl:value-of select="./attribute::ID_CATALOG"/>) </h4>
					
                </div>
            	<p>
					<p><strong>Ид родительского каталога: </strong><xsl:value-of select="./ID_PARENT"/></p>					
				</p>
            </div>
            
        </div>   
    </xsl:template>  
	
	<xsl:template match="PRODUCT">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
        	<div>
				<div style="text-align:left">
					<p><strong>Продукт</strong></p>	
				</div>
            	<div style="border-bottom-style:double; border-width:2px;">
        			<h4><xsl:value-of select="./NAME"/> (<xsl:value-of select="./attribute::ID_PRODUCT"/>) </h4>
                </div>
            	<p><xsl:value-of select="./DESCRIPTION"/> </p>
            </div>
            <div style="text-align:right">
            	<p><strong>Цена: </strong><xsl:value-of select="./PRICE"/> </p>	
            </div>
        </div>   
    </xsl:template>  
	
	<xsl:template match="OPINION">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
				<div style="text-align:left">
					<p><strong>Комментарии</strong></p>	
				</div>
            	<div style="border-bottom-style:double; border-width:2px;">
        			<p><strong>Ид комментария:  </strong><xsl:value-of select="./attribute::ID_OPINION"/></p> 
					<p><strong>Ид пользователя:  </strong><xsl:value-of select="./ID_USER"/></p>
				    <p><strong>Ид продукта:   </strong><xsl:value-of select="./ID_PRODUCT"/> </p>
					
	            </div>
				<p><xsl:value-of select="./TEXT"/></p> 
		</div>
    </xsl:template>  
	
	<xsl:template match="ORDER">  
    	<div style=" border-width:2px; border-style:groove; margin:20px; padding:20px; background-color:#CCC">
			<div style="text-align:left">
					<p><strong>Заказ</strong></p>	
			</div>
            <p><strong>Ид заказа:  </strong><xsl:value-of select="./attribute::ID_ORDER"/> </p>
			<p><strong>Ид пользователя:  </strong><xsl:value-of select="./ID_USER"/></p>
			<p><strong>Ид продукта:   </strong><xsl:value-of select="./ID_PRODUCT"/> </p>
			<p><strong>Статус:  </strong><xsl:value-of select="./STATUS"/></p>
			<p><strong>Количество:  </strong><xsl:value-of select="./KOL_VO"/></p>					
	    </div>   
    </xsl:template>  
    
</xsl:stylesheet>