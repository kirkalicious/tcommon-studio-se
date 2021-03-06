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
package org.talend.repository.items.importexport.ui.managers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.talend.utils.io.FilesUtils;

/**
 */
public class FilesManager extends AbstractImportResourcesManager {

    @Override
    public InputStream getStream(IPath path) throws IOException {
        return new BufferedInputStream(new FileInputStream((File) path2Object.get(path)));
    }

    @Override
    public boolean collectPath2Object(Object root) {
        return doCollectItemFiles((File) root);
    }

    private boolean doCollectItemFiles(File directory) {
        File[] contents = directory.listFiles();

        if (contents != null) {
            for (File content : contents) {
                File file = content;

                if (file.isFile()) {
                    add(file.getAbsolutePath(), file);
                }
                if (file.isDirectory()) {
                    if ((!FilesUtils.isSVNFolder(file))) {
                        collectPath2Object(content);
                    }
                }
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.localprovider.imports.ResourcesManager#getProvider()
     */
    @Override
    public IImportStructureProvider getProvider() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.localprovider.imports.ResourcesManager#closeResource()
     */
    @Override
    public void closeResource() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.items.importexport.manager.ResourcesManager#getRoot()
     */
    @Override
    public Object getRoot() {
        return null;
    }
}
