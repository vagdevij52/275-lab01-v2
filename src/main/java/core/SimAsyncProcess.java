package core;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class SimAsyncProcess extends SimProcess {
	private LinkedBlockingQueue<SimWork> _queue;
	private Thread _thread = null;
	private AsyncRun _runner;

	public SimAsyncProcess(int id) {
		super(id);

		_queue = new LinkedBlockingQueue<SimWork>();
	}

	@Override
	public void run(SimWork w) {
		_queue.add(w);
	}

	@Override
	public void startup() {
		if (_thread == null) {
			_runner = new AsyncRun();
			_thread = new Thread(_runner);
			_thread.start();
		}
	}

	@Override
	public void shutdown() {
		if (_thread != null) {
			try {
				_runner.stop();
				_thread.join();
				_thread = null;
				_runner = null;
			} catch (InterruptedException e) {
				// ack!
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean hasWork() {
		return _queue.size() > 0;
	}

	/**
	 * must implement this method to perform work asynchronously
	 */
	public abstract void doRun();

	protected Iterator<SimWork> getQueueIterator() {
		return _queue.iterator();
	}

	/**
	 * inner class to do work asynchronously
	 * 
	 * @author gash
	 *
	 */
	public class AsyncRun implements Runnable {
		private boolean _forever = true;

		public AsyncRun() {
		}

		public void stop() {
			_forever = false;
		}

		@Override
		public void run() {
			SimWork w = null;
			while (_forever) {
				try {
					SimAsyncProcess.this._queue.poll(2000, TimeUnit.MILLISECONDS);
					SimAsyncProcess.this.doRun();
				} catch (InterruptedException e) {
					// ack!
					System.out.println(e.getMessage());
				}

			}
		}
	}
}
