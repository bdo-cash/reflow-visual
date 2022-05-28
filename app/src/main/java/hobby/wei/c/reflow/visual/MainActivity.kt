package hobby.wei.c.reflow.visual

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import hobby.wei.c.reflow.Reflow
import hobby.wei.c.reflow.implicits.P_NORMAL
import hobby.wei.c.reflow.implicits.SHORT
import hobby.wei.c.reflow.lite.Input
import hobby.wei.c.reflow.lite.Lite
import hobby.wei.c.reflow.lite.Task
import hobby.wei.c.reflow.visual.databinding.ActivityMainBinding
import scala.reflect.ClassTag

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val task: Lite<String, String> =
        Task.apply(
            SHORT(), P_NORMAL(), null, null, true,
            object : scala.Function2<String, hobby.wei.c.reflow.Task.Context, String> {
                override fun apply(v1: String?, v2: hobby.wei.c.reflow.Task.Context?): String {
                    TODO("Not yet implemented")
                }
            },
            ClassTag.apply(String::class.java), ClassTag.apply(String::class.java)
        )

    private val pulse by lazy {
        Reflow.setDebugMode(true)
        Input<String>(ClassTag.apply(String::class.java)).next(task) { String::class.java }
            .pulse(null, false, 128, 17, false, null, null)
    }

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