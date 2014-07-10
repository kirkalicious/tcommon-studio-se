// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata;

import java.sql.DatabaseMetaData;
import java.util.List;

import org.apache.log4j.Logger;
import org.talend.cwm.relational.TdColumn;
import orgomg.cwm.resource.relational.ColumnSet;

/**
 * created by Talend on Jul 9, 2014 Detailled comment
 * 
 */
public class ImpalaConnectionFillerImpl extends DBConnectionFillerImpl {

    private static Logger log = Logger.getLogger(ImpalaConnectionFillerImpl.class);

    @Override
    public List<TdColumn> fillColumns(ColumnSet colSet, IMetadataConnection iMetadataConnection, DatabaseMetaData dbJDBCMetadata,
            List<String> columnFilter, String columnPattern) {
        return super.fillColumns(colSet, dbJDBCMetadata, columnFilter, "%");
    }
}
