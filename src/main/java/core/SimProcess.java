package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import core.SimWork.WorkStatus;

public class SimProcess {
	private final int _id;
	private final Integer _mutex;
	private final Random _rand;
	private final int _maxSleep;

	private int _numRuns;
	private long _lastRan;
	private long _accumTime;

	private List<SimProcess> _connections;

	public SimProcess(int id) {
		_id = id;
		_mutex = Integer.valueOf(1);
		_rand = new Random(System.nanoTime());
		_maxSleep = 10; // msec

		// connectivity between processes
		_connections = new ArrayList<SimProcess>();

		reset();
	}

	/**
	 * Create a one-way connection to another process
	 * 
	 * @param to
	 */
	public void addConnection(SimProcess to) {
		if (to != null)
			_connections.add(to);
	}

	public Iterator<SimProcess> getConnections() {
		return _connections.iterator();
	}

	/**
	 * reset execution (simulation) stats
	 */
	public void reset() {
		_numRuns = 0;
		_lastRan = 0;
		_accumTime = 0;
	}

	/**
	 * initiates the process to do work
	 */
	public void startup() {
	}

	/**
	 * stops the process from doing work, runs cleanup
	 */
	public void shutdown() {
	}

	/**
	 * does the process have pending work
	 * 
	 * @return
	 */
	public boolean hasWork() {
		return false;
	}

	/**
	 * Do work
	 */
	public void run(SimWork w) throws SimException {
		synchronized (_mutex) {
			long st = System.currentTimeMillis();
			try {
				_numRuns++;
				_lastRan = System.currentTimeMillis();

				w.setStart(System.currentTimeMillis());
				w.setStatus(WorkStatus.Pending);
				w.setProcessedBy(_id);

				// --------------------------------------------
				// TODO do something here...evaluate success
				// --------------------------------------------
				Thread.sleep(_rand.nextInt(_maxSleep) + 1);

				w.setEnd(System.currentTimeMillis());
				w.setStatus(WorkStatus.Success);

			} catch (Exception e) {
				// Status is better than: _good = false;
				System.out.println(e.getMessage());
				w.setStatus(WorkStatus.Failed);
				throw new SimException(e);
			} finally {
				_accumTime += System.currentTimeMillis() - st;
			}
		}
	}

	/**
	 * @return unique ID of the process
	 */
	public int getId() {
		return _id;
	}

	/**
	 * @return number of times the process was called
	 */
	public int getNumRuns() {
		return _numRuns;
	}

	/**
	 * @return last time (msec) the process was ran
	 */
	public long getLastRan() {
		return _lastRan;
	}

	/**
	 * @return accumulated time (msec) of the process
	 */
	public long getAccumTime() {
		return _accumTime;
	}
}
