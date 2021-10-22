package core;

public class SimWork {
	public static final int sNOVALUE = Integer.MIN_VALUE;
	
	public enum WorkType {
		Work, Start, Pause, Shutdown
	};

	public enum WorkStatus {
		Unknown, Pending, Success, Failed
	};

	private int _id;
	private WorkType _type;
	private WorkStatus _status;
	private long _start; // start time
	private long _end; // end time
	private int _processedBy;

	private int _data; // placeholder

	public SimWork(int id) {
		_id = id;
		_status = WorkStatus.Unknown;
		_start = _end = _processedBy = SimWork.sNOVALUE;
	}

	public WorkType getType() {
		return _type;
	}

	public void setType(WorkType _type) {
		this._type = _type;
	}

	public WorkStatus getStatus() {
		return _status;
	}

	public void setStatus(WorkStatus _status) {
		this._status = _status;
	}

	public long getStart() {
		return _start;
	}

	public void setStart(long _start) {
		this._start = _start;
	}

	public long getEnd() {
		return _end;
	}

	public void setEnd(long _end) {
		this._end = _end;
	}

	public int getProcessedBy() {
		return _processedBy;
	}

	public void setProcessedBy(int _processedBy) {
		this._processedBy = _processedBy;
	}

	public int getData() {
		return _data;
	}

	public void setData(int _data) {
		this._data = _data;
	}

	public int getId() {
		return _id;
	}

}
