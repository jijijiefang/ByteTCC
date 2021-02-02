/**
 * Copyright 2014-2016 yangming.liu<bytefox@126.com>.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 */
package org.bytesoft.compensable;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.bytesoft.transaction.Transaction;
import org.bytesoft.transaction.TransactionManager;

/**
 * TCC全局事务管理器
 */
public interface CompensableManager extends TransactionManager {

	public CompensableTransaction getCompensableTransaction(Thread thread);

	/**
	 * 根据当前线程从map中获取分布式事务
	 * @return CompensableTransaction
	 */
	public CompensableTransaction getCompensableTransactionQuietly();

	public void attachThread(Transaction transaction);

	public Transaction detachThread();

	public void compensableBegin() throws NotSupportedException, SystemException;
	//分布式事务提交
	public void compensableCommit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, SystemException;

	public void compensableRollback() throws IllegalStateException, SecurityException, SystemException;

}
