package client;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import core.SimWork;



public class ClientAsyncProcess {

	private LinkedBlockingQueue<GrpcClient> _queue;
	private Thread _thread = null;
	private AsyncRun _runner;
	
	public void run(GrpcClient w) {
		_queue.add(w);
	}
	
	public void startup() {
		if (_thread == null) {
			_runner = new AsyncRun();
			_thread = new Thread(_runner);
			_thread.start();
		}
	}
	
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
	
	public boolean hasWork() {
		return _queue.size() > 0;
	}
	
	protected Iterator<GrpcClient> getQueueIterator() {
		return _queue.iterator();
	}
	
	public class AsyncRun implements Runnable {
		private boolean _forever = true;

		public AsyncRun() {
		}

		public void stop() {
			_forever = false;
		}

		@Override
		public void run() {
			GrpcClient w = null;
			while (_forever) {
				try {
					ClientAsyncProcess.this._queue.poll(2000, TimeUnit.MILLISECONDS);
					//ClientAsyncProcess.this.doRun();
				} catch (InterruptedException e) {
					// ack!
					System.out.println(e.getMessage());
				}

			}
		}
	}
}
