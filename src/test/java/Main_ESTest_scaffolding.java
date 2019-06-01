/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sat Jun 01 19:15:21 GMT 2019
 */

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.evosuite.runtime.sandbox.Sandbox;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EvoSuiteClassExclude
public class Main_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  protected static ExecutorService executor; 

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.krizsanandras.projectwob.Main"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    executor = Executors.newCachedThreadPool(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    executor.shutdownNow(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("file.encoding", "Cp1250"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "C:\\Users\\Illumi\\AppData\\Local\\Temp\\"); 
    java.lang.System.setProperty("user.country", "HU"); 
    java.lang.System.setProperty("user.dir", "D:\\Egyeb\\WOB\\restpostgre"); 
    java.lang.System.setProperty("user.home", "C:\\Users\\Illumi"); 
    java.lang.System.setProperty("user.language", "hu"); 
    java.lang.System.setProperty("user.name", "Illumi"); 
    java.lang.System.setProperty("user.timezone", "Europe/Prague"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(Main_ESTest_scaffolding.class.getClassLoader() ,
      "org.postgresql.core.QueryExecutor",
      "org.postgresql.util.GT",
      "org.postgresql.hostchooser.HostRequirement",
      "org.postgresql.hostchooser.HostRequirement$4",
      "org.hsqldb.lib.Iterator",
      "org.postgresql.core.CachedQuery",
      "org.hsqldb.jdbc.JDBCDriver",
      "org.postgresql.util.URLCoder",
      "org.postgresql.core.v3.TypeTransferModeRegistry",
      "org.postgresql.util.SharedTimer",
      "org.postgresql.jdbc.BatchResultHandler",
      "org.postgresql.core.BaseConnection",
      "org.postgresql.core.ResultHandler",
      "org.postgresql.copy.CopyOperation",
      "org.hsqldb.jdbc.JDBCDriver$1",
      "org.postgresql.PGNotification",
      "org.postgresql.util.Gettable",
      "org.postgresql.core.Version",
      "org.postgresql.util.LruCache",
      "org.postgresql.core.PGStream",
      "org.postgresql.ssl.WrappedFactory",
      "org.hsqldb.lib.FileAccess",
      "org.postgresql.core.EncodingPredictor$DecodeResult",
      "org.postgresql.core.PGBindException",
      "org.postgresql.core.Encoding",
      "org.postgresql.core.TypeInfo",
      "org.postgresql.hostchooser.HostChooser",
      "org.hsqldb.DatabaseURL",
      "org.postgresql.core.SocketFactoryFactory",
      "org.hsqldb.map.HashIndex",
      "org.postgresql.core.ResultCursor",
      "org.postgresql.replication.fluent.ChainedCreateReplicationSlotBuilder",
      "com.krizsanandras.projectwob.DatabaseHelper",
      "org.hsqldb.persist.HsqlProperties",
      "org.postgresql.sspi.ISSPIClient",
      "org.postgresql.core.ResultHandlerBase",
      "org.postgresql.jdbc.AutoSave",
      "org.postgresql.jdbc.PreferQueryMode",
      "org.hsqldb.types.TimestampData",
      "org.postgresql.core.ParameterList",
      "org.postgresql.util.HostSpec",
      "org.postgresql.jdbc.SslMode",
      "org.postgresql.util.CanEstimateSize",
      "org.postgresql.util.ServerErrorMessage",
      "org.postgresql.util.PSQLException",
      "org.postgresql.core.Provider",
      "org.hsqldb.map.ValuePoolHashMap",
      "org.postgresql.util.ExpressionProperties",
      "org.hsqldb.map.BaseHashMap",
      "org.hsqldb.lib.FileAccess$FileSync",
      "org.postgresql.fastpath.Fastpath",
      "org.postgresql.util.WriterHandler",
      "org.postgresql.core.TransactionState",
      "org.postgresql.largeobject.LargeObjectManager",
      "org.postgresql.copy.CopyManager",
      "org.postgresql.core.ConnectionFactory",
      "org.postgresql.core.v3.ConnectionFactoryImpl",
      "org.postgresql.jdbc.TimestampUtils",
      "org.postgresql.replication.PGReplicationConnection",
      "org.postgresql.replication.fluent.ChainedStreamBuilder",
      "org.postgresql.ssl.LibPQFactory",
      "org.postgresql.jdbc.PgConnection",
      "org.hsqldb.map.ValuePool",
      "org.postgresql.core.ReplicationProtocol",
      "com.krizsanandras.projectwob.Main",
      "org.hsqldb.lib.ObjectComparator",
      "org.postgresql.hostchooser.CandidateHost",
      "org.postgresql.core.Field",
      "org.postgresql.hostchooser.HostChooserFactory",
      "org.postgresql.util.PSQLState",
      "org.postgresql.PGProperty",
      "org.postgresql.PGConnection",
      "org.postgresql.hostchooser.SingleHostChooser",
      "org.postgresql.Driver$1",
      "org.postgresql.core.PGStream$1",
      "org.postgresql.hostchooser.HostStatus",
      "org.postgresql.hostchooser.HostRequirement$2",
      "org.postgresql.hostchooser.HostRequirement$3",
      "org.postgresql.core.Query",
      "org.postgresql.hostchooser.HostRequirement$1",
      "org.postgresql.Driver"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(Main_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.krizsanandras.projectwob.Main",
      "com.krizsanandras.projectwob.DatabaseHelper",
      "org.postgresql.util.SharedTimer",
      "org.postgresql.Driver",
      "org.hsqldb.jdbc.JDBCDriver",
      "org.postgresql.Driver$1",
      "org.postgresql.PGProperty",
      "org.postgresql.util.URLCoder",
      "org.postgresql.jdbc.PgConnection",
      "org.postgresql.util.HostSpec",
      "org.postgresql.core.ConnectionFactory",
      "org.postgresql.core.v3.ConnectionFactoryImpl",
      "org.postgresql.jdbc.SslMode",
      "org.postgresql.hostchooser.HostRequirement",
      "org.postgresql.core.SocketFactoryFactory",
      "org.postgresql.hostchooser.HostChooserFactory",
      "org.postgresql.hostchooser.SingleHostChooser",
      "org.postgresql.hostchooser.CandidateHost",
      "org.postgresql.core.PGStream",
      "org.postgresql.util.PSQLException",
      "org.postgresql.util.GT",
      "org.postgresql.util.PSQLState",
      "org.hsqldb.DatabaseURL",
      "org.hsqldb.persist.HsqlProperties",
      "org.hsqldb.map.BaseHashMap",
      "org.hsqldb.map.ValuePoolHashMap",
      "org.hsqldb.map.HashIndex",
      "org.hsqldb.map.ValuePool"
    );
  }
}
