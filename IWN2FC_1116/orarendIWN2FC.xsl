<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    /* Center tables for demo */
                    table {
                    margin: 0 auto;
                    }

                    /* Default Table Style */
                    table {
                    color: #333;
                    background: white;
                    border: 1px solid grey;
                    font-size: 12pt;
                    border-collapse: collapse;
                    }
                    table thead th,
                    table tfoot th {
                    color: #777;
                    background: rgba(0,0,0,.1);
                    }
                    table caption {
                    padding:.5em;
                    }
                    table th,
                    table td {
                    padding: .5em;
                    border: 1px solid lightgrey;
                    }
                    /* Zebra Table Style */
                    [data-table-theme*=zebra] tbody tr:nth-of-type(odd) {
                    background: rgba(0,0,0,.05);
                    }
                    [data-table-theme*=zebra][data-table-theme*=dark] tbody tr:nth-of-type(odd) {
                    background: rgba(255,255,255,.05);
                    }
                    /* Dark Style */
                    [data-table-theme*=dark] {
                    color: #ddd;
                    background: #333;
                    font-size: 12pt;
                    border-collapse: collapse;
                    }
                    [data-table-theme*=dark] thead th,
                    [data-table-theme*=dark] tfoot th {
                    color: #aaa;
                    background: rgba(0255,255,255,.15);
                    }
                    [data-table-theme*=dark] caption {
                    padding:.5em;
                    }
                    [data-table-theme*=dark] th,
                    [data-table-theme*=dark] td {
                    padding: .5em;
                    border: 1px solid grey;
                    }
                    h2 {
                        display: flex;
                        justify-content: center;
                    }
                </style>
            </head>
            <body>
                <h2>Orarend</h2>

                <table >
                    <tr bgcolor="grey">
                        <th>ID</th>
                        <th>Targy</th>
                        <th>Nap</th>
                        <th>Tol</th>
                        <th>Ig</th>
                        <th>Helyszin</th>
                        <th>Oktato</th>
                        <th>Szak</th>
                    </tr>

                    <xsl:for-each select="LM_orarend/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="idopont/nap"/></td>
                            <td><xsl:value-of select="idopont/tol"/></td>
                            <td><xsl:value-of select="idopont/ig"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
