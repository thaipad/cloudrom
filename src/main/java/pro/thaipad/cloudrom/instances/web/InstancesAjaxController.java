/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.thaipad.cloudrom.AuthorizedUser;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.instances.entity.Os;
import pro.thaipad.cloudrom.users.entity.User;
import sun.plugin2.message.Message;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

@RestController
@RequestMapping("/ajax/instances")
public class InstancesAjaxController extends AbstractInstancesController {

    @Autowired
    MessageSource messageSource;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Instance> getAll() {
        List<Instance> list = super.getAll();
        return list;
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Instance get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "run/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean run(@PathVariable("id") int id) {
        return super.run(id);
    }

    @Override
    @GetMapping(value = "/stop/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean stop(@PathVariable("id") int id) {
        return super.stop(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping(produces = "application/x-www-form-urlencoded; charset=UTF-8")
    public ResponseEntity<String> createOrUpdate(@RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("cpu") Integer cpu,
                                                 @RequestParam("ram") Integer ram,
                                                 @RequestParam("hdd") Integer hdd,
                                                 @RequestParam("osstr") String os,
                                                 Locale locale) {


        StringJoiner joiner = new StringJoiner("<br>");
        StringBuilder builder = new StringBuilder();
//        TODO: Have to implement max and min values of cpu, ram, hdd for current user

        if (name == null || name.isEmpty()) {
            joiner.add(messageSource.getMessage("instance.note.empty_name", new String[]{locale.getDisplayName(locale)}, locale));
        }
        if (os == null || os.isEmpty()) {
            joiner.add(messageSource.getMessage("instance.note.empty_os", new String[]{locale.getDisplayName(locale)}, locale));
        }
        if (cpu == null || cpu == 0) {
            joiner.add(messageSource.getMessage("instance.note.empty_cpu", new String[]{locale.getDisplayName(locale)}, locale));
        }
        if (ram == null || ram == 0) {
            joiner.add(messageSource.getMessage("instance.note.empty_ram", new String[]{locale.getDisplayName(locale)}, locale));
        }
        if (hdd == null || hdd == 0) {
            joiner.add(messageSource.getMessage("instance.note.empty_hdd", new String[]{locale.getDisplayName(locale)}, locale));
        }
        if (!joiner.toString().isEmpty()) {
            return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);

        }

        if (id == null || id == 0) {
            super.create(new Instance(null, name, description, cpu, ram, hdd, Os.valueOf(os), null));
        } else {
            super.update(new Instance(id, name, description, cpu, ram, hdd, Os.valueOf(os), null));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/name", produces = "application/x-www-form-urlencoded; charset=UTF-8")
    public ResponseEntity<String> updateOnlyName(@RequestParam("idInst") Integer id,
                                                 @RequestParam("nameInst") String name,
                                                 @RequestParam("descrInst") String description,
                                                 Locale locale) {


        StringJoiner joiner = new StringJoiner("<br>");

        if (name == null || name.isEmpty()) {
            joiner.add(messageSource.getMessage("instance.note.empty_name", new String[]{locale.getDisplayName(locale)}, locale));
            return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Instance instance = super.get(id);
        instance.setName(name);
        instance.setDescription(description);
        super.updateName(instance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
