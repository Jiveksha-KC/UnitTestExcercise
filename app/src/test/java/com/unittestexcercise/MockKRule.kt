package com.unittestexcercise

import io.mockk.MockKAnnotations
import org.junit.rules.MethodRule
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.Statement

/**
 * Adding this rule to your classes allows you to utilise the `@InjectMockks` from MockK to
 * instantiate your subject under test without having to initialise the annotations in a setup method.
 */
class MockKRule : MethodRule {

    override fun apply(base: Statement, method: FrameworkMethod, target: Any): Statement {
        return object : Statement() {
            override fun evaluate() {
                MockKAnnotations.init(target)
                base.evaluate()
            }
        }
    }
}