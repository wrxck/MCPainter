/*
 * The MIT License
 *
 * Copyright 2015 prime.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.PrimeSoft.MCPainter.asyncworldedit;

import com.sk89q.worldedit.MaxChangedBlocksException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.primesoft.asyncworldedit.AsyncWorldEditMain;
import org.primesoft.asyncworldedit.PlayerEntry;
import org.primesoft.asyncworldedit.PlayerManager;
import org.primesoft.asyncworldedit.blockPlacer.BlockPlacer;
import org.primesoft.asyncworldedit.blockPlacer.entries.JobEntry;
import org.primesoft.asyncworldedit.utils.FuncParamEx;
import org.primesoft.asyncworldedit.worldedit.AsyncTask;
import org.primesoft.asyncworldedit.worldedit.CancelabeEditSession;
import org.primesoft.asyncworldedit.worldedit.ThreadSafeEditSession;

/**
 *
 * @author SBPrime
 */
public class AWEWrapper {

    /**
     * Get instance of AWE plugin
     *
     * @return
     */
    private static AsyncWorldEditMain getAsyncWorldEdit(Plugin plugin) {
        try {
            Plugin wPlugin = plugin.getServer().getPluginManager().getPlugin("AsyncWorldEdit");

            if ((wPlugin == null) || (!(wPlugin instanceof AsyncWorldEditMain))) {
                return null;
            }

            return (AsyncWorldEditMain) wPlugin;
        } catch (NoClassDefFoundError ex) {
            return null;
        }
    }

    /**
     * Get AWE wrapper
     *
     * @param plugin
     * @return
     */
    public static AWEWrapper getWrapper(JavaPlugin plugin) {
        AsyncWorldEditMain aweMain = getAsyncWorldEdit(plugin);

        if (aweMain == null) {
            return null;
        }

        return new AWEWrapper(aweMain);
    }

    private final BlockPlacer m_blockPlacer;
    private final PlayerManager m_playerManager;

    private AWEWrapper(AsyncWorldEditMain aweMain) {
        m_blockPlacer = aweMain.getBlockPlacer();
        m_playerManager = aweMain.getPlayerManager();
    }

    public void runTask(Player player, String jobName, DrawingTask task) {
        final PlayerEntry playerEntry = m_playerManager.getPlayer(player);
        final ThreadSafeEditSession editSession;

        m_blockPlacer.PerformAsAsyncJob(editSession, playerEntry, jobName,
                task);
        
        !
    }
}