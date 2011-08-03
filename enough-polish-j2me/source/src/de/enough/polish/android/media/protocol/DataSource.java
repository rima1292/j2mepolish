//#condition polish.android
// generated by de.enough.doc2java.Doc2Java (www.enough.de) on Wed Jan 21 21:07:48 CET 2009
package de.enough.polish.android.media.protocol;

import de.enough.polish.android.media.Controllable;

/**
 * 
 * A <CODE>DataSource</CODE> is an abstraction for media protocol-handlers.
 * It hides the details of how the data is read from source--whether
 * the data is
 * coming from a file, streaming server or proprietary delivery mechanism.
 * It provides the methods for a <code>Player</code> to access
 * the input data.
 * <p>
 * An application-defined protocol can be implemented with a custom
 * <code>DataSource</code>.  A <code>Player</code> can then be
 * created for playing back the media from the custom
 * <code>DataSource</code> using the
 * <a href="../Manager.html#createPlayer(de.enough.polish.android.media.protocol.DataSource)">
 * <code>Manager.createPlayer</code></a> method.
 * <p>
 * There are a few reasons why one would choose to implement
 * a <code>DataSource</code> as opposed to an <code>InputStream</code>
 * for a custom protocol:
 * <ul>
 * <li>
 * <code>DataSource/SourceStream</code> provides the random
 * seeking API that
 * is not supported by an <code>InputStream</code>.  i.e., if
 * the custom protocol
 * requires random seeking capabilities, a custom
 * <code>DataSource</code> can be used.
 * <li>
 * <code>DataSource/SourceStream</code> supports the concept of
 * transfer size
 * that is more suited for frame-delimited data, e.g. video.
 * </ul>
 * <p>
 * A <code>DataSource</code> contains a set of <code>SourceStream</code>s.
 * Each <code>SourceStream</code> represents one elementary data stream
 * of the source.  In the most common case, a <code>DataSource</code>
 * only provides one <code>SourceStream</code>.  A <code>DataSource</code>
 * may provide multiple <code>SourceStream</code>s if it encapsulates
 * multiple elementary data streams.
 * <p>
 * Each of the <code>SourceStream</code>s provides the methods to allow
 * a <code>Player</code> to read data for processing.
 * <p>
 * <CODE>DataSource</CODE> manages the life-cycle of the media source
 * by providing a simple connection protocol.
 * 
 * <p>
 * <a name="controls">
 * <code>DataSource</code> implements <code>Controllable</code> which
 * provides extra controls via some type-specific <code>Control</code>
 * interfaces.  <code>getControl</code> and <code>getControls</code>
 * can only be called when the <code>DataSource</code> is connected.
 * An <code>IllegalStateException</code> will be thrown otherwise.
 * 
 * <A HREF="../../../../de/enough/polish/android/media/protocol/SourceStream.html" title="interface in de.enough.polish.android.media.protocol"><CODE>SourceStream</CODE></A>,
 * <A HREF="../../../../de/enough/polish/android/media/protocol/ContentDescriptor.html" title="class in de.enough.polish.android.media.protocol"><CODE>ContentDescriptor</CODE></A></DL>
 * <HR>
 * 
 */
public abstract class DataSource extends java.lang.Object implements Controllable
{
	//following variables are implicitely defined by getter- or setter-methods:
	private java.lang.String locator;

	/**
	 * Construct a <CODE>DataSource</CODE> from a locator.
	 * This method should be overloaded by subclasses;
	 * the default implementation just keeps track of
	 * the locator.
	 * <P>
	 * 
	 * @param locator - The locator that describes the DataSource.
	 */
	public DataSource(java.lang.String locator)
	{
		//TODO implement DataSource
	}

	/**
	 * Get the locator that describes this source.
	 * Returns <CODE>null</CODE> if the locator hasn't been set.
	 * <P>
	 * 
	 * 
	 * @return The locator for this source.
	 */
	public java.lang.String getLocator()
	{
		return this.locator;
	}

	/**
	 * Get a string that describes the content-type of the media
	 * that the source is providing.
	 * <P>
	 * 
	 * 
	 * @return The name that describes the media content. Returns null if the content is unknown.
	 * @throws java.lang.IllegalStateException - Thrown if the source is not connected.
	 */
	public abstract java.lang.String getContentType();

	/**
	 * Open a connection to the source described by
	 * the locator and initiate communication.
	 * <P>
	 * 
	 * 
	 * @throws java.io.IOException - Thrown if there are IO problems when connect is called.
	 * @throws java.lang.SecurityException - Thrown if the caller does not have security permission to call connect.
	 */
	public abstract void connect() throws java.io.IOException;

	/**
	 * Close the connection to the source described by the locator
	 * and free resources used to maintain the connection.
	 * <p>
	 * If no resources are in use, <CODE>disconnect</CODE> is ignored.
	 * If <CODE>stop</CODE> hasn't already been called,
	 * calling <CODE>disconnect</CODE> implies a stop.
	 * <P>
	 * 
	 */
	public abstract void disconnect();

	/**
	 * Initiate data-transfer. The <CODE>start</CODE> method must be
	 * called before data is available for reading.
	 * <P>
	 * 
	 * 
	 * @throws java.lang.IllegalStateException - Thrown if the DataSource is not connected.
	 * @throws java.io.IOException - Thrown if the DataSource cannot be started due to some IO problems.
	 * @throws java.lang.SecurityException - Thrown if the caller does not have security permission to call start.
	 */
	public abstract void start() throws java.io.IOException;

	/**
	 * Stop the data-transfer.
	 * If the <code>DataSource</code> has not been connected and started,
	 * <CODE>stop</CODE> is ignored.
	 * <P>
	 * 
	 * 
	 * @throws java.io.IOException - Thrown if the DataSource cannot be stopped due to some IO problems.
	 */
	public abstract void stop() throws java.io.IOException;

	/**
	 * Get the collection of streams that this source
	 * manages. The collection of streams is entirely
	 * content dependent. The  MIME type of this
	 * <CODE>DataSource</CODE> provides the only indication of
	 * what streams may be available on this connection.
	 * <P>
	 * 
	 * 
	 * @return The collection of streams for this source.
	 * @throws java.lang.IllegalStateException - Thrown if the source is not connected.
	 */
	public abstract SourceStream[] getStreams();

}