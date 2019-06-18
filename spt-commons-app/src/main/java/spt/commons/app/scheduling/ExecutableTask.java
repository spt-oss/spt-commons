
package spt.commons.app.scheduling;

/**
 * Executable task
 */
public interface ExecutableTask {
	
	/**
	 * Cron prefix
	 */
	String CRON_PREFIX = "${";
	
	/**
	 * Cron suffix
	 */
	String CRON_SUFFIX = ".cron}";
	
	/**
	 * Enabled
	 */
	String ENABLED = "enabled";
	
	/**
	 * Execute
	 */
	void execute();
}
