# RESTful API Design
## Critial Questions
### Should database id be exposed to the endpoint URL path, e.g. sth like `/lexical_item/1`

# Quarkus
## [Dev services for database](https://quarkus.io/guides/databases-dev-services#:~:text=Dev%20Services%20for%20databases%20automatically,The%20application%20is%20configured%20automatically.&text=Dev%20Services%20for%20databases%20relies,which%20is%20run%20in%20process)
> When testing or running in dev mode Quarkus can provide you with a zero-config database out of the box

## Critical questions
### Does Quarkus framework have embedded server like the embedded Tomcat inside Spring Boot (20260421)
Yes. [Vert.x](https://quarkus.io/guides/vertx-reference)
## Caveats
### `@Embedded` annotation
#### From https://github.com/rxue/dictionary/issues/172 (revised on 20260421)
~`@Embedded` class is not supported to be in an abstract `@Entity` class. This kinda just obey the "composition over inheritance rule"~

#### From https://github.com/rxue/dictionary/issues/174 on 20250529 (revised on 20260421)
~When using `quarkus-resteasy-jsonb` to make REST API endpoint, the attribute annotated with `@Embedded` inside a *JPA entity* will not be visible in the implemented REST API endpoint response JSON. This is a bit different from that when using `resteasy` of *WildFly* to implement the REST API. In order to make the `@Embedded` attribute visible in the response JSON, add *getter* and *setter* to that `@Embedded` field.~ Moreover, the `@Embedded` annotation is not compulsory, i.e. it can be removed

The statements above was WRONG. The `AbstractEntity` code is the following:

```
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Embedded
    private DateAttributes dateAttributes;
}

```

The error is:

```
2025-05-17 18:12:46,652 ERROR [io.qua.dep.dev.IsolatedDevModeMain] (main) Failed to start quarkus: java.lang.RuntimeException: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
	[error]: Build step io.quarkus.deployment.steps.ClassTransformingBuildStep#handleClassTransformation threw an exception: java.lang.reflect.UndeclaredThrowableException
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:862)
	at io.quarkus.builder.BuildContext.run(BuildContext.java:255)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.doRunWith(EnhancedQueueExecutor.java:2675)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2654)
	at org.jboss.threads.EnhancedQueueExecutor.runThreadBody(EnhancedQueueExecutor.java:1627)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1594)
	at java.base/java.lang.Thread.run(Thread.java:840)
	at org.jboss.threads.JBossThread.run(JBossThread.java:499)
Caused by: java.util.concurrent.ExecutionException: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.handleClassTransformation(ClassTransformingBuildStep.java:273)
	at java.base/java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:732)
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:856)
	... 8 more
Caused by: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:146)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.hibernateEnhancement(HibernateEntityEnhancer.java:86)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.visitEnd(HibernateEntityEnhancer.java:79)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at io.quarkus.panache.common.deployment.visitors.PanacheEntityClassAccessorGenerationVisitor.visitEnd(PanacheEntityClassAccessorGenerationVisitor.java:126)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:749)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:425)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.transformClass(ClassTransformingBuildStep.java:367)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:238)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:225)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	... 7 more
Caused by: java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.InlineDirtyCheckingHandler.wrap(InlineDirtyCheckingHandler.java:91)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.fieldWriter(PersistentAttributeTransformer.java:329)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.applyTo(PersistentAttributeTransformer.java:281)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.doEnhance(EnhancerImpl.java:366)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.lambda$enhance$1(EnhancerImpl.java:135)
	at org.hibernate.bytecode.internal.bytebuddy.ByteBuddyState.rewrite(ByteBuddyState.java:190)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:135)
	... 18 more

	at io.quarkus.runner.bootstrap.AugmentActionImpl.runAugment(AugmentActionImpl.java:372)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:289)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:61)
	at io.quarkus.deployment.dev.IsolatedDevModeMain.firstStart(IsolatedDevModeMain.java:89)
	at io.quarkus.deployment.dev.IsolatedDevModeMain.accept(IsolatedDevModeMain.java:428)
	at io.quarkus.deployment.dev.IsolatedDevModeMain.accept(IsolatedDevModeMain.java:55)
	at io.quarkus.bootstrap.app.CuratedApplication.runInCl(CuratedApplication.java:138)
	at io.quarkus.bootstrap.app.CuratedApplication.runInAugmentClassLoader(CuratedApplication.java:93)
	at io.quarkus.deployment.dev.DevModeMain.start(DevModeMain.java:107)
	at io.quarkus.deployment.dev.DevModeMain.main(DevModeMain.java:70)
Caused by: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
	[error]: Build step io.quarkus.deployment.steps.ClassTransformingBuildStep#handleClassTransformation threw an exception: java.lang.reflect.UndeclaredThrowableException
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:862)
	at io.quarkus.builder.BuildContext.run(BuildContext.java:255)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.doRunWith(EnhancedQueueExecutor.java:2675)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2654)
	at org.jboss.threads.EnhancedQueueExecutor.runThreadBody(EnhancedQueueExecutor.java:1627)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1594)
	at java.base/java.lang.Thread.run(Thread.java:840)
	at org.jboss.threads.JBossThread.run(JBossThread.java:499)
Caused by: java.util.concurrent.ExecutionException: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.handleClassTransformation(ClassTransformingBuildStep.java:273)
	at java.base/java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:732)
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:856)
	... 8 more
Caused by: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:146)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.hibernateEnhancement(HibernateEntityEnhancer.java:86)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.visitEnd(HibernateEntityEnhancer.java:79)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at io.quarkus.panache.common.deployment.visitors.PanacheEntityClassAccessorGenerationVisitor.visitEnd(PanacheEntityClassAccessorGenerationVisitor.java:126)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:749)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:425)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.transformClass(ClassTransformingBuildStep.java:367)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:238)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:225)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	... 7 more
Caused by: java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.InlineDirtyCheckingHandler.wraeDirtyCheckingHandler.java:91)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.fieldWriter(PersistentAttributeTransformer.java:329)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.applyTo(PersistentAttributeTransformer.java:281)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.doEnhance(EnhancerImpl.java:366)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.lambda$enhance$1(EnhancerImpl.java:135)
	at org.hibernate.bytecode.internal.bytebuddy.ByteBuddyState.rewrite(ByteBuddyState.java:190)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:135)
	... 18 more

	at io.quarkus.builder.Execution.run(Execution.java:122)
	at io.quarkus.builder.BuildExecutionBuilder.execute(BuildExecutionBuilder.java:78)
	at io.quarkus.deployment.QuarkusAugmentor.run(QuarkusAugmentor.java:161)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.runAugment(AugmentActionImpl.java:368)
	... 9 more
Caused by: java.lang.reflect.UndeclaredThrowableException
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:862)
	at io.quarkus.builder.BuildContext.run(BuildContext.java:255)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.doRunWith(EnhancedQueueExecutor.java:2675)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2654)
	at org.jboss.threads.EnhancedQueueExecutor.runThreadBody(EnhancedQueueExecutor.java:1627)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1594)
	at java.base/java.lang.Thread.run(Thread.java:840)
	at org.jboss.threads.JBossThread.run(JBossThread.java:499)
Caused by: java.util.concurrent.ExecutionException: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.handleClassTransformation(ClassTransformingBuildStep.java:273)
	at java.base/java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:732)
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:856)
	... 8 more
Caused by: org.hibernate.bytecode.enhance.spi.EnhancementException: Failed to enhance class io.github.rxue.dictionary.jpa.entity.LexicalItem
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:146)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.hibernateEnhancement(HibernateEntityEnhancer.java:86)
	at io.quarkus.hibernate.orm.deployment.HibernateEntityEnhancer$HibernateEnhancingClassVisitor.visitEnd(HibernateEntityEnhancer.java:79)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at io.quarkus.panache.common.deployment.visitors.PanacheEntityClassAccessorGenerationVisitor.visitEnd(PanacheEntityClassAccessorGenerationVisitor.java:126)
	at org.objectweb.asm.ClassVisitor.visitEnd(ClassVisitor.java:395)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:749)
	at org.objectweb.asm.ClassReader.accept(ClassReader.java:425)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep.transformClass(ClassTransformingBuildStep.java:367)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:238)
	at io.quarkus.deployment.steps.ClassTransformingBuildStep$2.call(ClassTransformingBuildStep.java:225)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	... 7 more
Caused by: java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.InlineDirtyCheckingHandler.wrap(InlineDirtyCheckingHandler.java:91)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.fieldWriter(PersistentAttributeTransformer.java:329)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.PersistentAttributeTransformer.applyTo(PersistentAttributeTransformer.java:281)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.doEnhance(EnhancerImpl.java:366)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.lambda$enhance$1(EnhancerImpl.java:135)
	at org.hibernate.bytecode.internal.bytebuddy.ByteBuddyState.rewrite(ByteBuddyState.java:190)
	at org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl.enhance(EnhancerImpl.java:135)
	... 18 more
```

The error is due to missing of *setter* and *getter* on `DateAttributes` other than the use of `@Embedded` annotation


# 20260420
## Problem encountered
### end-to-end test with `jest` failed



