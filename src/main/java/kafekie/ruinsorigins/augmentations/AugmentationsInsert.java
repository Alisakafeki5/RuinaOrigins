package kafekie.ruinsorigins.augmentations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kafekie.ruinsorigins.Ruinaorigins;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AugmentationsInsert {
public record Workshops(List<String> contains, String command, String unequip_command) {}
    public static class WorkshopLoader extends JsonDataLoader implements IdentifiableResourceReloadListener {

    public static final Map<Identifier, List<Workshops>> WORKSHOPS = new HashMap<>();

        public WorkshopLoader() {
            super(new Gson(), "workshops");
        }

        @Override
        protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
            WORKSHOPS.clear();

            for (var entry : prepared.entrySet()) {
                Identifier id = entry.getKey();
                JsonObject json = entry.getValue().getAsJsonObject();

                List<Workshops> workshops = new ArrayList<>();
                JsonArray array = null;

                if (json.has("workshops")) {
                    array = json.getAsJsonArray("workshops");
                } else if (json.has("powers")) {
                    array = json.getAsJsonArray("powers");
                }

                if (array == null) continue;
                for (JsonElement e : array) {
                    JsonObject obj = e.getAsJsonObject();

                    List<String> contains = new ArrayList<>();
                    obj.getAsJsonArray("contains")
                            .forEach(el -> contains.add(el.getAsString()));

                    String command = obj.get("command").getAsString();
                    String unequip_command = obj.get("unequip_command").getAsString();

                    workshops.add(new Workshops(contains, command, unequip_command));
                }

                WORKSHOPS.put(id, workshops);
            }
        }

        @Override
        public Identifier getFabricId() {
            return new Identifier("ruins-origins", "workshops");
        }
    }


}

