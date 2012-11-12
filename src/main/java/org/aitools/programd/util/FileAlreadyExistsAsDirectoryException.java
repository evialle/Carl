/*
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version. You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package org.aitools.programd.util;

import java.io.File;

/**
 * This exception indicates that an attempt to create a file cannot be
 * fulfilled, because there is already a directory with the requested name.
 * 
 * @author <a href="mailto:noel@aitools.org">Noel Bush</a>
 * @since 4.5
 */
public class FileAlreadyExistsAsDirectoryException extends Exception
{
    /**
     * Creates a new FileAlreadyExistsAsDirectoryException.
     * 
     * @param directory the directory that already exists
     */
    public FileAlreadyExistsAsDirectoryException(File directory)
    {
        super("\"" + directory.getAbsolutePath() + "\" already exists as a directory.");
    }
}
