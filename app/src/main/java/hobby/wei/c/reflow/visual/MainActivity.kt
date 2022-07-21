package hobby.wei.c.reflow.visual

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import hobby.wei.c.reflow.Pulse
import hobby.wei.c.reflow.Reflow
import hobby.wei.c.reflow.Task.Context
import hobby.wei.c.reflow.implicits.P_NORMAL
import hobby.wei.c.reflow.implicits.SHORT
import hobby.wei.c.reflow.lite.Input
import hobby.wei.c.reflow.lite.Lite
import hobby.wei.c.reflow.lite.Task
import hobby.wei.c.reflow.visual.compat.classTag
import hobby.wei.c.reflow.visual.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import scala.Option
import scala.runtime.AbstractFunction2

//import hobby.wei.c.reflow.Pulse.`Feedback$`.`MODULE$`

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val task: Lite<String, String> =
        Task.apply(
            SHORT(), P_NORMAL(), "task name", null, true,
            object : AbstractFunction2<String, Context, String>() {
                override fun apply(s: String?, ctx: Context?): String {
                    TODO("Not yet implemented")
                }
            },
            classTag.string, classTag.string
        )

//    private val pulse by lazy {
//        Reflow.setDebugMode(true)
//        Input(classTag.string).next(task, classTag.string)
//            .pulse(object : `MODULE$`.Lite<String>(0) {
//                override fun liteOnComplete(serialNum: Long, value: Option<String>?) {
//                    TODO("Not yet implemented")
//                }
//            }, false, 128, 17, false, null, null)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        lifecycleScope.launch(Dispatchers.IO) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}