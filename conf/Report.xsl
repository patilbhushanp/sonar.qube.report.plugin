<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Sonar Issue Reports</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Project</th>
      <th style="text-align:left">Issue Description</th>
	  <th style="text-align:left">Last Updated By</th>
	  <th style="text-align:left">Severity</th>
	  <th style="text-align:left">Issue Category</th>
	  <th style="text-align:left">Issue Status</th>
    </tr>
    <xsl:for-each select="root/array">
    <tr>
      <td><xsl:value-of select="project"/></td>
      <td><xsl:value-of select="message"/></td>
	  <td><xsl:value-of select="substring-before(updateDate, 'T')"/></td>
	  <td><xsl:value-of select="severity"/></td>
	  <td>
		  <xsl:for-each select="tags">
			<xsl:value-of select="."/>,
		  </xsl:for-each>
	  </td>
	  <td><xsl:value-of select="status"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

