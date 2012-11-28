package com.qpeka.api;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class RequestHandlerServer {
	
	private int port = 8000;
	private AtomicBoolean started = new AtomicBoolean(false);
	private NioServerSocketChannelFactory factory = null;
	private ServerBootstrap bootstrap = null;
		
	private static RequestHandlerServer instance = null;
	
	public static RequestHandlerServer getInstance()
	{
		if(instance == null)
			instance = new RequestHandlerServer();
		
		return instance;
	}
	
	private  RequestHandlerServer()
	{
		
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void start()
	{
		if(!started.getAndSet(true))
		{		  
			factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool());
			bootstrap = new ServerBootstrap(factory);
			// Set up the pipeline factory.
			bootstrap.setPipelineFactory(new UserrRequestServerPipelineFactory());
				   
			// Bind and start to accept incoming connections.
			bootstrap.bind(new InetSocketAddress(8080));
			
			System.out.println("Server started on port " + port);
		
		}
		else
		{
			System.out.println("Server already started");
		}
	}
	
	public void stop()
	{
		if(started.get())
		{
			factory.releaseExternalResources();
			bootstrap.releaseExternalResources();
			factory = null;
			bootstrap = null;
			System.out.println("Server stopped");
		}
		else
		{
			System.out.println("Server already stopped");
		}
	}
	
	public static void main(String[] args) {
		
		RequestHandlerServer.getInstance().start();
	}
}
