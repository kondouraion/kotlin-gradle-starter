import kotlinx.coroutines.*

fun main(args:Array<String>) = runBlocking<Unit> {
	println("Hello World.")

	GlobalScope.launch {
			println("in coroutines")
	}

	// https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/basics.md#your-first-coroutine
	runBlocking { 
		Thread.sleep(100L)
	}
	
	coroutineScope { 
		launch {
			delay(500L) 
			println("Task from nested launch")	
		}
		delay(100L)
		println("Task from coroutine scope")
	}

	repeat(100_000) {
		launch { 
				delay(10L)
				print(".")
		}
	}
	
	(1..100_000).map { i ->
		launch {
			delay(100L)
			print(i)
		}
	}

	(1..100_000).map { i ->
		val ret = async<Int> {
			i*i
		}
		ret.await()
	}.sum().let(::println)
}
