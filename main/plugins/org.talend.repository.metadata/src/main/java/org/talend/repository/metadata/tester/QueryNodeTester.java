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
package org.talend.repository.metadata.tester;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.tester.SubNodeTester;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class QueryNodeTester extends SubNodeTester {

    private static final String IS_QUERY = "isQuery"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.tester.AbstractNodeTester#testProperty(java.lang.Object, java.lang.String,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    protected Boolean testProperty(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) receiver;
            if (IS_QUERY.equals(property)) {
                return isQuery(repositoryNode);
            }
        }
        return null;
    }

    public boolean isQuery(RepositoryNode repositoryNode) {
        return isTypeNode(repositoryNode, ERepositoryObjectType.METADATA_CON_QUERY);
    }

    @Override
    public ERepositoryObjectType findParentItemType(RepositoryNode repositoryNode) {
        final ERepositoryObjectType objectType = repositoryNode.getObjectType();
        if (objectType == ERepositoryObjectType.METADATA_CON_QUERY) {
            // RepositoryNode parent = repositoryNode.getParent();
            // if (parent != null) {
            // parent = parent.getParent();
            // }
            // if (parent != null) {
            // return parent.getObjectType();
            // }
            if (repositoryNode.getObject() != null) {
                // FIXME there should be a problem for the performance, when getProperty() for IRepositoryViewObject
                ERepositoryObjectType parentType = ERepositoryObjectType.getItemType(repositoryNode.getObject().getProperty()
                        .getItem());
                return parentType;
            }
        }
        return null;
    }

}
