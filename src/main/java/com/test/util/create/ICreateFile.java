/**
 * 
 */
package com.test.util.create;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface ICreateFile {
	public void create(String tableName);
	public void create(EntityInfos entityInfos);
	public void create(List<EntityInfos> entityInfosList);
}
